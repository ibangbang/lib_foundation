package lib.foundation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ibangbang@qq.com
 * @version 1.0
 * @since 2019/3/9
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    public Response() {
    }

    public Response(String c, String m) {
        this(c, m, null);
    }

    public Response(String c, String m, T d) {
        this.code = c;
        this.msg = m;
        this.data = d;
    }

    public String code;
    public String msg;

    public T data;

    public String flag;
    public String content;

    public boolean isEncrypt() {
        return "1".equals(flag);
    }

    public boolean isSucc() {
        return "0".equals(code);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

