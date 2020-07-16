package bookrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Marketing_table")
public class Marketing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String message;
    private int targetStartAge;
    private int targetStopAge;
    private String targetSex;
    private String marketingType;
    private Date regDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTargetStartAge() {
        return targetStartAge;
    }
    public void setTargetStartAge(int targetStartAge) {
        this.targetStartAge = targetStartAge;
    }

    public Integer getTargetStopAge() {
        return targetStopAge;
    }
    public void setTargetStopAge(int targetStopAge) {
        this.targetStopAge = targetStopAge;
    }

    public String getTargetSex() {
        return targetSex;
    }
    public void setTargetSex(String targetSex) {
        this.targetSex = targetSex;
    }

    public String getMarketingType() {
        return marketingType;
    }
    public void setMarketingType(String marketingType) {
        this.marketingType = marketingType;
    }
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }




}
