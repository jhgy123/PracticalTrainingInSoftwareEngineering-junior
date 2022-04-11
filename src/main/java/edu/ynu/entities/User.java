package edu.ynu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int userId;
    private String userName;
    private String password;
    private String organization;
    private String sex;

    @Override
    public String toString(){
        return"\t"+this.userId + "\t"
                +this.userName +"\t"
                +this.organization +"\t"
                +this.sex +"  \t"
                +"\n";
    }
}



