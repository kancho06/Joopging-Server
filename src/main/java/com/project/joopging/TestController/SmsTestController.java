package com.project.joopging.TestController;


import com.google.gson.JsonArray;
import com.project.joopging.schedule.SmsSchedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SmsTestController {

    private final SmsSchedule smsSchedule;

//    @GetMapping("/test3")
//    public ResponseDto send() {
//        JsonArray toList = new JsonArray();
//        String number = "01099403102";
//        toList.add(number);
//        String message = "안녕하세요 줍깅입니다. " +
//                "https://forms.gle/X3nQmmbHiRwmmWtZ8";
//        smsSchedule.sendSms(toList,message);
//        return new ResponseDto(200L,"테스트 성공","");
//    }
}
