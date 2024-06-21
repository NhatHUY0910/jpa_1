package com.practice_jpa_1.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "tên không được để trống")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "tên chỉ được chứa chữ cái, không được chứa kí tự đặc biệt và số")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Pattern(regexp = "^(?=.*[0-9])[A-Za-z0-9._%+-]{4,}@gmail\\.com$", message = "Email phải có chứa chuỗi @gmail.com và có ít nhất một chữ số")
    @Size(min = 16, max = 32, message = "Email phải chứa từ 16 đến 32 kí tự")
    private String email;

    @NotNull(message = "tuổi không được để trống")
    @Min(value = 5, message = "không đủ tuổi, tuổi không được dưới 5")
    @Max(value = 199, message = "please go back to the ground")
    private int age;

    @NotNull(message = "ngày sinh không được để trống")
    @Past(message = "ngày sinh phải ở quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Customer() {
    }

    public Customer(String name, String email, int age, Date birthday, Province province) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.province = province;
    }

    public Customer(Long id, String name, String email, int age, Date birthday, Province province) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
