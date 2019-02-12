package cn.mldn.util.tools;

import javax.servlet.http.HttpServletRequest;

public class Validator {
    /**
     * 实现数据验证的操作
     * @param request
     * @param rule 验证的操作规则
     * @return
     */
    public boolean validate(HttpServletRequest request,String rule) {
        boolean flag = true ;
        String result [] = rule.split("\\|") ;
        for (int x = 0 ; x < result.length ; x ++) {
            String temp [] = result[x].split(":") ;
            String value = request.getParameter(temp[0]) ;  // 取得参数内容
            if (value != null) {
                if ("string".equals(temp[1])) {
                    flag = this.validateString(value) ;
                } else if ("number".equals(temp[1])) {
                    flag = this.validateNumber(value) ;
                } else if ("date".equals(temp[1])) {
                    flag = this.validateDate(value) ;
                }
                if(flag == false) { // 没有验证成功
                    request.setAttribute(temp[0],"ruleError");
                }
            } else {    // 没有数据
                flag = false ;  // 表示没有内容
                request.setAttribute(temp[0],"valueError");
            }
        }
        return flag ;
    }
    public boolean validateString(String str) {   // 验证String数据
        if (str == null || "".equals(str)) {
            return false ;
        }
        return true ;
    }
    public boolean validateNumber(String str) {
        if (this.validateString(str)) { // 有内容
            return str.matches("\\d+(\\.\\d+)?") ;
        }
        return false ;
    }
    public boolean validateDate(String str) {
        if (this.validateString(str)) {
            if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return true ;
            } else {
                return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
            }
        }
        return false ;
    }
}
