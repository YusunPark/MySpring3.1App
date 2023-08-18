package com.ktds.myspringboot.service;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.entity.User;
import com.ktds.myspringboot.exception.BusinessException;
import com.ktds.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor // final로 선언된 변수의 생성자를 바로 만들어준다. (개발자가 생성자를 안만들어도 된다.)
public class UserService {

    // Autowired 는 mock객체가 안되어서 Constructor 인젝션 주입을 사용한다.
    // final을 쓰면 -> 생성자가 있어야 한다.
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserResDto saveUser(UserReqDto userReqDto) {
        // reqDto -> entity
        User user = modelMapper.map(userReqDto, User.class);//input으로 받은 usrReqDto를 Entity로 변환
        User saveUser = userRepository.save(user); //entity를 저장

        // entity -> resDto
        return modelMapper.map(saveUser, UserResDto.class);

    }

    @Transactional(readOnly = true)
    public UserResDto getUserById(Long id) {
        // id -> entity
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));

        // entity -> resDto
        return modelMapper.map(user, UserResDto.class);
    }

    @Transactional(readOnly = true)
    public List<UserResDto> getUsers() {
        // db에서 entity를 List로 불러온다.
        List<User> users = userRepository.findAll();

        // entity -> Stream<UserResDto>
        // Stream으로 바꿔서 하는 것이 간단 (Stream의 Function인터페이스 사용)
        Stream<UserResDto> userResDtoStream = users.stream()
                .map(user -> modelMapper.map(user, UserResDto.class));

        // Stream<User> -> List<UserResDto>
        // collect는 위에 userResDtoStream만들때 map뒤에서 체이닝 해도된다.
        return userResDtoStream.collect(toList());
    }


    public UserResDto updateUser(String email, UserReqDto userReqDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));

        // setter 호출 : 정보 업데이트 (email은 변경 불가능)
        // save 등을 안하고 setter만 처리해도 update가 된다 -> 더티체킹 -> 전제조건, Transactional이 되어야한다.
        // put -> 모든 항목을 수정
        // fetch -> 부분 수정
        user.setName(userReqDto.getName());

        return modelMapper.map(user, UserResDto.class);
    }
}
