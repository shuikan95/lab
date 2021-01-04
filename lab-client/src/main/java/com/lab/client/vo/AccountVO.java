package com.lab.client.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountVO {
    @Range(min = 0, max = 1000, message = "id范围大于0，小于2000！")
    private Integer id;
}
