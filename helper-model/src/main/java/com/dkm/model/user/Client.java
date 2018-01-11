package com.dkm.model.user;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Client extends BaseEntity implements Serializable {

    private String clientName;
    private String clientId;
    private String clientSecret;


}
