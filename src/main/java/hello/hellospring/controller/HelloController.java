package hello.hellospring.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.processing.Generated;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello");     //
        return "hello";     //resources의 hello.html로 연결해라

    }

        @GetMapping("hello-mvc")
        public String helloMvc(@RequestParam("name") String name, Model model) {    //외부에서 파라미터를 받아 모델에 담아
            model.addAttribute("name", name);   //파라미터에서 넘어온 이름을 넘겨
            return "hello-template";
        }

    @GetMapping("hello-string") //api방식 중 문자로 하는 방식
    @ResponseBody //http에서 body부에 이 data를 직접 그대로 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();  //ctrl+shift+enter:마무리
        hello.setName(name);    //파라미터로 넘어온 네임을 넣어줘
        return hello;   //객체를 넘김
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    //json방식: 키,벨류로 이루어짐.
    }
