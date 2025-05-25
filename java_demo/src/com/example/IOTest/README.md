# IO
## 字节流
byte[]
java.io.InputStream读
java.io.OutputStream写
FileInputStream fileInputStream = new FileInputStream("d:\\a.txt");
fileInputStream.read();从输入流读取一个字节的数据，返回读取的字节值（0 - 255），到达文件末尾返回 -1。
fileInputStream.read(byte[] b);尝试将最多 b.length 个字节的数据读入字节数组 b 中，返回实际读取的字节数，到达文件末尾返回 -1。
fileInputStream.read(byte[] b, int off, int len);
```java
FileInputStream fileInputStream = new FileInputStream("d:\\a.txt");
byte[] bytes = new byte[1024];
int len;
while ((len = fileInputStream.read(bytes)) != -1) {
    System.out.println(new String(bytes, 0, len));
}
```

FileOutputStream fileOutputStream = new FileOutputStream("d:\\a.txt");
fileOutputStream.write(int b);
fileOutputStream.write(byte[] b);
```java
FileOutputStream fileOutputStream = new FileOutputStream("d:\\a.txt");
fileOutputStream.write("Hello, World!".getBytes());
```
## 字符流
char[]
java.io.Reader读
java.io.Writer写
FileReader fileReader = new FileReader("d:\\a.txt");
fileReader.read();从输入流读取一个字符的数据，返回读取的字符值（0 - 65535），到达文件末尾返回 -1。
fileReader.read(char[] c);尝试将最多 cbuf.length 个字符的数据读入字符数组 cbuf 中，返回实际读取的字符数，到达文件末尾返回 -1。
fileReader.read(char[] c, int off, int len);
```java
FileReader fileReader = new FileReader("d:\\a.txt");
int data;
while ((data = fileReader.read())!= -1) {
    System.out.print((char) data); 
}
```

FileWriter fileWriter = new FileWriter("d:\\a.txt");
fileWriter.write(int c);
fileWriter.write(String str);
void flush()：刷新输出流，将缓冲中的数据写入底层输出流。
```java
FileWriter fileWriter = new FileWriter("d:\\a.txt");
fileWriter.write("Hello, World!");
```
## 缓冲流
BufferedReader bufferedReader = new BufferedReader(new FileReader("d:\\a.txt"));
int read()：从输入流读取一个字符的数据，返回读取的字符值（0 - 65535），到达文件末尾返回 -1。
int read(char[] cbuf, int off, int len)：尝试将最多 len 个字符的数据读入字符数组 cbuf 中，从索引 off 开始存储，返回实际读取的字符数，到达文件末尾返回 -1。
String readLine()：读取一行文本，返回该行内容，到达文件末尾返回 null。
void close()：关闭输入流并释放相关系统资源。
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("d:\\a.txt"));
void write(int c)：将指定的字符写入输出流。
void write(String str, int off, int len)：将字符串 str 中从索引 off 开始的 len 个字符写入输出流。
void newLine()：写入一个行分隔符。
void flush()：刷新输出流，将缓冲中的数据写入底层输出流。
void close()：关闭输出流并释放相关系统资源。
```java
BufferedReader bufferedReader = new BufferedReader(new FileReader("d:\\a.txt"));
String line;
while ((line = bufferedReader.readLine())!= null) {
    System.out.println(line);
}
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("d:\\a.txt"));
bufferedWriter.write("Hello, World!");
bufferedWriter.close();
```

## 转换流
### InputStreamReader
InputStreamReader 是字节流到字符流的桥梁，它将字节流转换为字符流。
### 构造方法
InputStreamReader(InputStream in)：使用平台默认的字符集创建 InputStreamReader 对象。
InputStreamReader(InputStream in, String charsetName)：使用指定的字符集创建 InputStreamReader 对象。
### 常用方法
int read()：从输入流读取一个字符的数据，返回读取的字符值（0 - 65535），到达文件末尾返回 -1。
int read(char[] cbuf)：尝试将最多 cbuf.length 个字符的数据读入字符数组 cbuf 中，返回实际读取的字符数，到达文件末尾返回 -1。
void close()：关闭输入流并释放相关系统资源。
```java
InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("d:\\a.txt"), "UTF-8");
inputStreamReader.read();
int data;
while ((data = inputStreamReader.read()) != -1) {
    System.out.print((char) data);
}
```


### OutputStreamWriter
OutputStreamWriter 是字符流到字节流的桥梁，它将字符流转换为字节流。
### 构造方法
OutputStreamWriter(OutputStream out)：使用平台默认的字符集创建 OutputStreamWriter 对象。
OutputStreamWriter(OutputStream out, String charsetName)：使用指定的字符集创建 OutputStreamWriter 对象。
```java
OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("d:\\a.txt"), "UTF-8");
outputStreamWriter.write("Hello, World!");
```