package ru.savinov.shop.utils.security;

import lombok.ToString;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.Collection;

import static ru.savinov.shop.common.ConstantProperties.ANONYMOUS_USER;

@ToString
public class CustomAccessByTimeDecisionVoter implements AccessDecisionVoter<Object> {

    private String permitTimeFrom = "08:15";
    private String permitTimeTo = "23:40";

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {

        if (nowIsPermitTime(permitTimeFrom, permitTimeTo) || ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            return ACCESS_GRANTED;
        }
        return ACCESS_DENIED;
    }

    private boolean nowIsPermitTime(String permitTimeFrom, String permitTimeTo) {
        LocalDateTime now = LocalDateTime.now().withDayOfYear(1);
        LocalDateTime timeFrom = getBorderPermitLocalDateTime(permitTimeFrom);
        LocalDateTime timeTo = getBorderPermitLocalDateTime(permitTimeTo);
        int afterFrom = now.compareTo(timeFrom);
        int beforeTo = timeTo.compareTo(now);
        return afterFrom>0 && beforeTo>0;
    }

    public LocalDateTime getBorderPermitLocalDateTime(String timeBorder) {
        int hour = Integer.parseInt(timeBorder.substring(0, 2));
        int minute = Integer.parseInt(timeBorder.substring(3));
        return LocalDateTime.now().withDayOfYear(1).withHour(hour).withMinute(minute);
    }

}


