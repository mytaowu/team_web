package com.panzh.vo;

/**
 * @author: TMingYi
 * @date: 2020/6/7 9:53
 * 图片上传类的返回类型
 */
public class ImageInfoVO {

    private boolean success;

    private String msg;

    private String file_path;


    public ImageInfoVO(boolean success, String file_path) {
        this.success = success;
        this.file_path = file_path;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
