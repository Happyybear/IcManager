/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sem.kingapputils.data.response;

/**
 *
 * Create by abc at 19/10/11
 * @author abc
 */
public class ResponseStatus {

    private String responseCode = "";
    private String message;
    private boolean success = true;
    private Enum source = ResultSource.NETWORK;

    public ResponseStatus() {
    }

    public ResponseStatus(String responseCode, boolean success) {
        this.responseCode = responseCode;
        this.success = success;
    }

    /**
     * 获取信息失败
     * @param success s
     */
    public ResponseStatus( boolean success) {
        this.responseCode = "-1";
        this.success = success;
    }

    public ResponseStatus(String responseCode, boolean success, Enum source) {
        this(responseCode, success);
        this.source = source;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Enum getSource() {
        return source;
    }

    public String getMessage(){
        return getMessageInfo(this.responseCode);
    }

    private String getMessageInfo(String responseCode){
        String result = "";
        switch (responseCode){
            case "-1":{
                result = "获取数据失败";
            }
            break;
            default:
        }
        return result;
    }
}
