package com.sensetime.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jason wang
 * @since 2019-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Device对象", description="")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String deviceSerialNo;

    private String deviceSecret;

    private String deviceCnName;

    private String deviceEnName;

    private String devicePort;

    private String deviceIp;

    private String deviceUri;

    private String deviceVersion;

    private String deviceConnType;

    private String deviceProtocol;

    private LocalDateTime lastCheckedTs;

    private String productTypeuuid;

    private String deviceTypeuuid;

    private String deviceTag;

    private Double matchingThreshold;

    private String description;

    private Integer sts;

    private String createUser;

    private LocalDateTime createTs;

    private String lastModUser;

    private LocalDateTime lastModTs;


}
