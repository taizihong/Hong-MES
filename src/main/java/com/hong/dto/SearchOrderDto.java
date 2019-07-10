package com.hong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchOrderDto {

    private String keyword;

    private Date fromTime;//yyyy-MM-dd HH:mm:ss

    private Date toTime;
	
    private Integer search_status=0;
}
