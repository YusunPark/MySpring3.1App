package com.ktds.myspringboot.service;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.entity.User;
import com.ktds.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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

}
