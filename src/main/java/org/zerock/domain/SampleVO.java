package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //생성자 = new SampleVO(mno, firstName, lastName)
@NoArgsConstructor //기본생성자 = new SampleVO()
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;

}
