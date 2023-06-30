package site.suimu.system.script;


import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//@Service
public class ScriptService {


    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        m4();
    }

    public static void m1() throws ScriptException {
        // 创建一个 JavaScript 引擎
        ScriptEngineManager manager = new ScriptEngineManager();
        System.out.println(manager.getEngineFactories());
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.toString());

        // 定义一个 JSON 字符串作为入参
        String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

        // 将 JSON 字符串传递给 JavaScript 脚本并执行
        engine.eval("var jsonData = " + jsonString + ";");
        engine.eval("jsonData.name = 'Tom'");
        String resStr = engine.eval("jsonData").toString();
        System.out.println(resStr);
    }

    public static void m2() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        //建立上下文
        Bindings bind = engine.createBindings();
        bind.put("factor", 1);
        System.out.println("请输入初始化参数：");
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        while (item != "exit") {
            Object eval = engine.eval(new FileReader("/Users/lennon/Documents/dump/script/demo.js"));
            if (engine instanceof Invocable) {
                System.out.println("输入参数：" + item);
                Invocable in = (Invocable) engine;
                String result = (String) in.invokeFunction("printWord", item);
                System.out.println("运算结果：" + result.toString());
            }
            System.out.println("请再次输入参数：");
            item = input.nextLine();
        }
    }


    public static void m3() throws ScriptException {
        // 创建一个 JavaScript 引擎
        ScriptEngineManager manager = new ScriptEngineManager();

        System.out.println(manager.getEngineFactories());
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 定义 JSON 数据作为入参
        String jsonData = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}";

        // 设置 JSON 数据为 JavaScript 上下文中的变量
        ScriptContext context = engine.getContext();
        context.setAttribute("data", jsonData, ScriptContext.ENGINE_SCOPE);

        // 执行 JavaScript 代码
        String script = "var person = JSON.parse(data);" +
                "person.name = 'Jane Doe';" +
                "JSON.stringify(person);";
        Object result = engine.eval(script);
        // 将结果转换为字符串并打印
        String resultJson = (String) result;
        System.out.println("处理后的结果：" + resultJson);
    }

    public static void m4() throws ScriptException{
        // 创建一个 JavaScript 引擎
        ScriptEngineManager manager = new ScriptEngineManager();
        System.out.println(manager.getEngineFactories());
        ScriptEngine engine = manager.getEngineByName("js");

        // 定义 JSON 数据作为入参
        String jsonData = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}";

        // 设置 JSON 数据为 JavaScript 上下文中的变量
        ScriptContext context = engine.getContext();
        context.setAttribute("data", jsonData, ScriptContext.ENGINE_SCOPE);
        // 执行 JavaScript 代码
        String script = """
                var person = JSON.parse(data);
                person.name = "Tom";
                JSON.stringify(person);
                 """;

        Object result = engine.eval(script);

        // 将结果转换为字符串并打印
        String resultJson = (String) result;
        System.out.println("处理后的结果：" + resultJson);
    }
}
