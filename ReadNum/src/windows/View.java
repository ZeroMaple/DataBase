package windows;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import utils.FileCountTest;
import utils.ReadFiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
/**
 * 
 * @author zero
 * @Swing-轻量级的框架
 * awt-重量级的框架-
 * @GUI graphic user interface
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	//创建窗口组件
	JFrame jFrame = new JFrame("文件数量查询软件");
	//创建输入区域
	JTextArea jTextArea = new JTextArea("");
	//创建操作按钮
	JTextField jTextField_excel = new JTextField();
	JButton jButton_excel = new JButton("Excel文件");//查看所有按钮
	JTextField jTextField_folder = new JTextField();
	JButton jButton_folder = new JButton("图片位置");//删除单个按钮
	JButton jButton_start = new JButton("启动软件");//增加信息按钮
	JButton jButton_clear = new JButton("清除信息");//修改信息按钮
/*	JButton jButton_searchOne = new JButton("查询单个");//查询单个按钮
	JButton jButton_init = new JButton("初始化");*/
	public static void main(String[] args) {
		new View().init();
	}
	public void init() {//初始化方法
		//设置窗口布局
		jFrame.setLayout(null);
		jFrame.setBounds(20, 20, 800, 600);//单位显示是像素
		jTextArea.setBounds(5,5,785,300);
		jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);        //激活自动换行功能 
		jTextArea.setWrapStyleWord(true);  
		jTextArea.setFont(new Font("微软雅黑",Font.BOLD,20));
		jTextArea.setText("请选择Excel表\n");
		jTextArea.append("请选择图片目录");
		jTextField_excel.setBounds(200, 360, 400, 40);
		jButton_excel.setBounds(650,360,100,40);
		jTextField_folder.setBounds(200, 420, 400, 40);
		jButton_folder.setBounds(650, 420, 100, 40);
		jButton_start.setBounds(280, 490, 100, 40);
		jButton_clear.setBounds(450, 490, 100, 40);
		//组件添加到jFrame
		jFrame.add(jTextArea);
		jFrame.add(jButton_excel);
		jFrame.add(jButton_folder);
		jFrame.add(jButton_start);
		jFrame.add(jButton_clear);
		jFrame.add(jTextField_excel);
		jFrame.add(jTextField_folder);
		
		excelListener();
		folderListener();
		startListener();
		clearListener();
		//设置空间的显示
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	//给按钮添加监听事件
	public void excelListener(){
		jButton_excel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        JFileChooser jfc=new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
		        jfc.showDialog(new JLabel(), "选择");
		        String excelPath = "";
		        if(jfc.getSelectedFile()!=null){
		        File file=jfc.getSelectedFile();
		        excelPath = file.getAbsolutePath();
		        jTextField_excel.setText(excelPath);
		        System.out.println(excelPath);
		        }
			}
		});
	}
	public void folderListener(){
		//初始化按钮的监听方法
		jButton_folder.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent e) {
		        // TODO Auto-generated method stub
		        JFileChooser jfc=new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        jfc.showDialog(new JLabel(), "选择");
		        if(jfc.getSelectedFile()!=null){
		        File file=jfc.getSelectedFile();
		        String filePath = "";
		        if(file.isDirectory()){
		        	filePath=file.getAbsolutePath();
		        	jTextField_folder.setText(filePath);
		            System.out.println("文件夹:"+filePath);
		        }else if(file.isFile()){
		            System.out.println("文件:"+file.getAbsolutePath());
		        }
		        System.out.println(jfc.getSelectedFile().getName());
		        }
		    }
		});
	}
	public void startListener() {
		jButton_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String excel_path = jTextField_excel.getText().replaceAll("\\\\", "/");
				ArrayList<Integer> columnList = (ArrayList<Integer>) ReadFiles.getExcelNum(excel_path);
				int excelNum=0;
				for(int i =0;i<columnList.size();i++){
					excelNum=excelNum+columnList.get(i);
				}
                // 获取map中的key集合，遍历map
                Set<Map.Entry<Integer, Double>> entrySetRead = ReadFiles.countResult.entrySet();
                Iterator<Entry<Integer, Double>> itRead = entrySetRead.iterator();
                int i =0;
                double va = 0.0;
                while(itRead.hasNext()){
                    Entry<Integer,Double> entryRead = itRead.next();
                    i=entryRead.getKey();
                    va=entryRead.getValue();
                    jTextArea.setText("第"+i+"表中页数之和为："+va);
                }
                jTextArea.setText("Excel各表中页数之和为："+excelNum+"\n");
				//
		    	String file_path = jTextField_folder.getText().replaceAll("\\\\", "/");
		    	FileCountTest.getFileType(file_path);  
		        
		        // 获取map中的key集合，遍历map  
		        Set<Map.Entry<String, Integer>> entrySet = FileCountTest.resultMap.entrySet();  
		        Iterator<Entry<String, Integer>> it = entrySet.iterator();  
		        int maxNum = 0;
		        int jpgNum = 0;
		        while (it.hasNext()) {  
		            Entry<String, Integer> entry = it.next();
		            if(entry.getKey().equals("jpg")){
		            	jpgNum = entry.getValue();
		            }
		            maxNum = maxNum+entry.getValue();
		        }
		        jTextArea.append("当前文件目录jpg图片数为:"+jpgNum+"\n");
		        jTextArea.append("当前文件目录文件数为："+maxNum+"\n");
		        FileCountTest.resultMap.clear();
		    }  
		});
	}
	public void clearListener() {
		jButton_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextArea.setText("请重新选择Excel表\n");
				jTextArea.append("请重新选择图片目录");
			}
		});
	}
}  

//Test

