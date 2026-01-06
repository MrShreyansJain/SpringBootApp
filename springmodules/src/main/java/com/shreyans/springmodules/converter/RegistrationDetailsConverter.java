package com.shreyans.springmodules.converter;

import com.shreyans.springmodules.entity.RegistrationDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class RegistrationDetailsConverter implements Converter<String, RegistrationDetails> {
    @Override
    public RegistrationDetails convert(String source) {
        String[] p = source.split("\\|");

        RegistrationDetails r = new RegistrationDetails();
        r.setStudentID(Long.valueOf(p[1]));
        r.setRegistrationDate(Date.valueOf(p[2])); // if in yyyy-MM-dd format
        r.setGovernmentID(p[3]);
        r.setNative(Boolean.valueOf(p[4]));

        return r;
    }
}
