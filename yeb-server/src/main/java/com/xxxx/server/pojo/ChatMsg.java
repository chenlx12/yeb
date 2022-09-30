
package com.xxxx.server.pojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * 消息
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {
    //发送人
    private String from;
    //发到那里去
    private String to;
    //内容
    private String content;
    //时间
    private LocalDateTime date;
    //昵称
    private String fromNickName;
}
