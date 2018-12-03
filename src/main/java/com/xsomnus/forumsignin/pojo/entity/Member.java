package com.xsomnus.forumsignin.pojo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-03 23:33
 **/
@Data
public class Member implements Serializable {
    private String name;
    @Id
    private String telephone;
    private Long signTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(telephone, member.telephone) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone);
    }
}
