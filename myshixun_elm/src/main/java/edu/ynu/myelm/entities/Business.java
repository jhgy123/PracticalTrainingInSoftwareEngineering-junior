package edu.ynu.myelm.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "Business_Inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
//类生成相对略微复杂的构建器API
//如：Student.builder()
//        .sno( "001" )
//        .sname( "admin" )
//        .sage( 18 )
//        .sphone( "110" )
//        .build();

@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class Business {
    @Id//设置主键

    @GeneratedValue(strategy=GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    private int id;
    @Column(length = 20,nullable = false)//设置字段的最大长度为20,非空
    private String password;
    @Column(length = 40,nullable = false)//设置字段的最大长度为40,非空
    private String name;
    @Column(length = 50)//设置字段的最大长度为50
    private String address;
    @Column(length = 40)//设置字段的最大长度为40
    private String explain;
    @Column(precision = 5,scale =2,columnDefinition="decimal(5,2) default 0.0")//设置字段数字宽度为5，小数位数为2，设置字段默认值为0.0
    private double starPrice;
    @Column(precision = 5,scale =2,columnDefinition="decimal(5,2) default 0.0")//设置字段数字宽度为5，小数位数为2,设置字段默认值为0.0
    private double deliveryPrice;
//    @OneToOne(targetEntity = Teacher.class)//设置对应的实体类的类型
//    @JoinColumn(name = "teacherid", referencedColumnName = "id")//设置外键
}
