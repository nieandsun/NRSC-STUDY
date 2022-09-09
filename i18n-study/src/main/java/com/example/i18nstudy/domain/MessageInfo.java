package com.example.i18nstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2022-09-09
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {
    private String code;
    private String locale;
    private String message;
}
