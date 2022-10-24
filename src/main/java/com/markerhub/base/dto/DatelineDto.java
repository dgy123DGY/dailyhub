package com.markerhub.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DatelineDto implements Serializable {

	private String title;

	private List<DatelineDto> children = new ArrayList<>();

}
