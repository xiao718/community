package com.community.xiao.community.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table(name = "gituser")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int id;
    private String account;
    private String name;
    private  String token;
    private Timestamp gmt_created;
    private Timestamp gmt_updated;
}
