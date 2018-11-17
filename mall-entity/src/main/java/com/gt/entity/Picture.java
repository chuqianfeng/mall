package com.gt.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cqf
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("picture")
public class Picture extends Model<Picture> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 店铺id
     */
    @TableField("res_id")
    private Integer resId;
    /**
     * 图片名称
     */
    @TableField("pic_name")
    private String picName;
    /**
     * 价格
     */
    private Double price;
    /**
     * 1正常0下架
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("creat_time")
    private Date creatTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
