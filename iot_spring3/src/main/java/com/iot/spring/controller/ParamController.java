package com.iot.spring.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.spring.vo.ObjVO;

@Controller
@RequestMapping("/param")
public class ParamController {
   private static final Logger log = LoggerFactory.getLogger(ParamController.class);

   @RequestMapping("/jsonRequestBodyToMap")
   public @ResponseBody Map<String,Object> jsonRequestBodyToMap(@RequestBody Map <String,Object>map){
      log.info("requestBodyToMap=>{}", map);
      return map;
   }
   @RequestMapping("/jsonRequestBodyToObject")
   public @ResponseBody ObjVO jsonRequestBodyToObject(@RequestBody ObjVO obj){
      log.info("requestBodyToObject=>{}", obj);
      return obj;
   }
   
   @RequestMapping("/formRequestBodyToMap")
   public @ResponseBody Map<String,Object> formRequestBodyToMap(@RequestBody Map <String,Object>map){
      log.info("formRequestBodyToMap=>{}", map);
      return map;
   }

   @RequestMapping("/formRequestBodyToObject")
   public @ResponseBody ObjVO formRequestBodyToObject(@RequestBody ObjVO obj){
      log.info("formRequestBodyToObject=>{}", obj);
      return obj;
   }

   @RequestMapping("/jsonRequestParamToMap")
   public @ResponseBody Map<String,Object> jsonRequestParamToMap(@RequestParam(value="obj",required=false) Map <String,Object>map){
      log.info("jsonRequestParamToMap=>{}", map);
      return map;
   }

   @RequestMapping("/jsonRequestParamToObject")
   public @ResponseBody ObjVO jsonRequestParamToObject(@RequestParam(value="obj") String objStr) throws IOException{
      //obj키값으로 string으로 받은 이후 변환해 사용
      ObjectMapper om = new ObjectMapper();
      ObjVO obj = om.readValue(objStr, ObjVO.class);
      log.info("jsonRequestParamToObject=>{}", obj);
      return obj;
   }

   @RequestMapping("/formRequestParamToMap")
   public @ResponseBody Map<String,Object> formRequestParamToMap(@RequestParam Map <String,Object>map){
      log.info("formRequestParamToMap=>{}", map);
      return map;
   }

   @RequestMapping("/formRequestParamToObject")
   public @ResponseBody ObjVO formRequestParamToObject(@RequestParam ObjVO obj){
      log.info("formRequestParamToObject=>{}", obj);
      return obj;
   }
   
   @RequestMapping("/formToMap")
   public @ResponseBody Map<String,Object> formToMap(Map <String,Object>map){
      log.info("formToMap=>{}", map);
      return map;
   }
   
   @RequestMapping("/formToObject")
   public @ResponseBody ObjVO formToObject(ObjVO obj){
      log.info("formToObject=>{}", obj);
      return obj;
   }
}