package bookrental;

import bookrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    UserInfoRepository userInfoRepo;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUserRegistered_ChangeUserInfo(@Payload UserRegistered userRegistered){

        if(userRegistered.isMe()){
            System.out.println("##### listener ChangeUserInfo : " + userRegistered.toJson());

            UserInfo userInfo = new UserInfo();
            userInfo.setId(userRegistered.getId());
            userInfo.setUserName(userRegistered.getUserName());
            userInfo.setRegTime(userRegistered.getRegTime());
            userInfo.setBirth(userRegistered.getBirth());
            userInfo.setSex(userRegistered.getSex());
            userInfo.setPhoneNumber(userRegistered.getPhoneNumber());
            userInfo.setEmailAddress(userRegistered.getEmailAddress());
            userInfo.setRegTime(userInfo.getRegTime());

            userInfoRepo.save(userInfo);
        }
    }

}
