package bookrental;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.awt.image.IntegerComponentRaster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
 public class MarketingController {

 @Autowired
 MarketingRepository marketingRepository;

 @Autowired
 UserInfoRepository userInfoRepository;

 @PostMapping("/marketings/register")
 public Marketing registered(@RequestBody Marketing postMarketingInfo) {

  System.out.println("##### registered!! promotion Msg : " + postMarketingInfo.getMessage());

  Marketing marketingInfo = new Marketing();
  marketingInfo.setMessage(postMarketingInfo.getMessage());
  marketingInfo.setTargetStartAge(postMarketingInfo.getTargetStartAge());
  marketingInfo.setTargetStopAge(postMarketingInfo.getTargetStopAge());
  marketingInfo.setTargetSex(postMarketingInfo.getTargetSex());
  marketingInfo.setMarketingType(postMarketingInfo.getMarketingType());
  marketingInfo.setRegDate(new Date());
  marketingRepository.save(marketingInfo);

  findTargetUser(marketingInfo);

  return marketingInfo;
 }

 private void findTargetUser(Marketing marketing) {
  UserInfo userInfo = null;
  List<UserInfo> userInfoList = userInfoRepository.findAll();
  if (!userInfoList.isEmpty()) {
   Iterator<UserInfo> iterator = userInfoList.iterator();
   while (iterator.hasNext()) {
    userInfo = iterator.next();

    if (decisionTargetAge(userInfo.getBirth(), marketing.getTargetStartAge(), marketing.getTargetStopAge())) {
     if (marketing.getTargetSex() != null) {
      if (userInfo.getSex().equals(marketing.getTargetSex())) {
       sendMarketing(marketing, userInfo);
      } else {
       System.out.println("not Target Sex!! user Sex=" + userInfo.getSex() + ". target Sex=" + marketing.getTargetSex());
      }
     } else {
      sendMarketing(marketing, userInfo);
     }
    }
   }
  }
 }

 private void sendMarketing(Marketing marketing, UserInfo userInfo) {
  if ("SMS".equals(marketing.getMarketingType())) {
   System.out.println("send SMS to External System!!");

   PromotionSmsSent promotionSmsSent = new PromotionSmsSent();
   promotionSmsSent.setId(marketing.getId());
   promotionSmsSent.setMessage(marketing.getMessage());
   promotionSmsSent.setPhoneNumber(userInfo.getPhoneNumber());
   promotionSmsSent.setReqDate(new Date());
   promotionSmsSent.publish();
  } else if ("MAIL".equals(marketing.getMarketingType())) {
   System.out.println("send MAIL to External System!!");

   PromotionMailSent promotionMailSent = new PromotionMailSent();
   promotionMailSent.setId(marketing.getId());
   promotionMailSent.setMessage(marketing.getMessage());
   promotionMailSent.setAddress(userInfo.getEmailAddress());
   promotionMailSent.setReqDate(new Date());
   promotionMailSent.publish();
  } else {
   System.out.println("can't send SMS or MAIL. MarketingType is " + marketing.getMarketingType());
  }
 }

 private boolean decisionTargetAge(String birth, int targetStartAge, int targetStopAge) {
  System.out.println("user birth=" + birth + " targetStartAge=" + targetStartAge + " targetStopAge=" + targetStopAge);

  if (targetStartAge == 0 && targetStopAge == 0) {
   return true;
  }

  Calendar currnent = Calendar.getInstance();
  int currentYear = currnent.get(Calendar.YEAR);
  int currentMonth = currnent.get(Calendar.MONTH) + 1;
  int currentDay = currnent.get(Calendar.DAY_OF_MONTH);

  System.out.println("currentYear=" + currentYear + " currentMonth=" + currentMonth + " currentDay=" + currentDay);

  int age = currentYear - Integer.parseInt(birth.substring(0, 4));
  int month = Integer.parseInt(birth.substring(4, 6));
  int day = Integer.parseInt(birth.substring(6, 8));

  System.out.println("age=" + age + " month=" + month + " day=" + day);

  if (month * 100 + day > currentMonth * 100 + currentDay) {
   age--;
  }

  System.out.println("user age=" + age);

  if( age >= targetStartAge && age <= targetStopAge ){
   return true;
  }
  else {
   return false ;
  }
 }
}
