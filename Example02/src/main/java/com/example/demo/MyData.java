package com.example.demo;

import com.example.demo.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mydata")
@NamedQueries({
        @NamedQuery(
                name = "findWithName",
                query = "from MyData where name like :fname"
        ),
        @NamedQuery(
                name = "findByAge",
                query = "from MyData where age > :min and age < :max"
        )
})
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotEmpty(message = "공백을 입력할 수 없습니다.")
    private String name;

    @Column(length = 200, nullable = true)
    @Email(message = "메일 주소 형식이 아닙니다.")
    private String mail;

    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column(nullable = true)
    @Phone
    private String memo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
