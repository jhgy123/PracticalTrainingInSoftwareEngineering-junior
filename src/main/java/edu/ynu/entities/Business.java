package edu.ynu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String password;
    private String businessExplain;
    private Double startPrice;
    private Double deliverPrice;

    @Override
    public String toString(){
        return "\n商家编号："+this.businessId+
                "\n商家名称："+this.businessName+
                "\n商家地址："+this.businessAddress+
                "\n商家介绍："+this.businessExplain+
                "\n起送费：¥"+this.startPrice+
                "\n配送费：¥"+this.deliverPrice+"\n";
    }


}
