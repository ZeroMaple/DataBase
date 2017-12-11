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
 * @Swing-�������Ŀ��
 * awt-�������Ŀ��-
 * @GUI graphic user interface
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	//�����������
	JFrame jFrame = new JFrame("�ļ�������ѯ���");
	//������������
	JTextArea jTextArea = new JTextArea("");
	//����������ť
	JTextField jTextField_excel = new JTextField();
	JButton jButton_excel = new JButton("Excel�ļ�");//�鿴���а�ť
	JTextField jTextField_folder = new JTextField();
	JButton jButton_folder = new JButton("ͼƬλ��");//ɾ��������ť
	JButton jButton_start = new JButton("�������");//������Ϣ��ť
	JButton jButton_clear = new JButton("�����Ϣ");//�޸���Ϣ��ť
/*	JButton jButton_searchOne = new JButton("��ѯ����");//��ѯ������ť
	JButton jButton_init = new JButton("��ʼ��");*/
	public static void main(String[] args) {
		new View().init();
	}
	public void init() {//��ʼ������
		//���ô��ڲ���
		jFrame.setLayout(null);
		jFrame.setBounds(20, 20, 800, 600);//��λ��ʾ������
		jTextArea.setBounds(5,5,785,300);
		jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);        //�����Զ����й��� 
		jTextArea.setWrapStyleWord(true);  
		jTextArea.setFont(new Font("΢���ź�",Font.BOLD,20));
		jTextArea.setText("��ѡ��Excel��\n");
		jTextArea.append("��ѡ��ͼƬĿ¼");
		jTextField_excel.setBounds(200, 360, 400, 40);
		jButton_excel.setBounds(650,360,100,40);
		jTextField_folder.setBounds(200, 420, 400, 40);
		jButton_folder.setBounds(650, 420, 100, 40);
		jButton_start.setBounds(280, 490, 100, 40);
		jButton_clear.setBounds(450, 490, 100, 40);
		//�����ӵ�jFrame
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
		//���ÿռ����ʾ
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	//����ť��Ӽ����¼�
	public void excelListener(){
		jButton_excel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        JFileChooser jfc=new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
		        jfc.showDialog(new JLabel(), "ѡ��");
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
		//��ʼ����ť�ļ�������
		jButton_folder.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent e) {
		        // TODO Auto-generated method stub
		        JFileChooser jfc=new JFileChooser();
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        jfc.showDialog(new JLabel(), "ѡ��");
		        if(jfc.getSelectedFile()!=null){
		        File file=jfc.getSelectedFile();
		        String filePath = "";
		        if(file.isDirectory()){
		        	filePath=file.getAbsolutePath();
		        	jTextField_folder.setText(filePath);
		            System.out.println("�ļ���:"+filePath);
		        }else if(file.isFile()){
		            System.out.println("�ļ�:"+file.getAbsolutePath());
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
                // ��ȡmap�е�key���ϣ�����map
                Set<Map.Entry<Integer, Double>> entrySetRead = ReadFiles.countResult.entrySet();
                Iterator<Entry<Integer, Double>> itRead = entrySetRead.iterator();
                int i =0;
                double va = 0.0;
                while(itRead.hasNext()){
                    Entry<Integer,Double> entryRead = itRead.next();
                    i=entryRead.getKey();
                    va=entryRead.getValue();
                    jTextArea.setText("��"+i+"����ҳ��֮��Ϊ��"+va);
                }
                jTextArea.setText("Excel������ҳ��֮��Ϊ��"+excelNum+"\n");
				//
		    	String file_path = jTextField_folder.getText().replaceAll("\\\\", "/");
		    	FileCountTest.getFileType(file_path);  
		        
		        // ��ȡmap�е�key���ϣ�����map  
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
		        jTextArea.append("��ǰ�ļ�Ŀ¼jpgͼƬ��Ϊ:"+jpgNum+"\n");
		        jTextArea.append("��ǰ�ļ�Ŀ¼�ļ���Ϊ��"+maxNum+"\n");
		        FileCountTest.resultMap.clear();
		    }  
		});
	}
	public void clearListener() {
		jButton_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextArea.setText("������ѡ��Excel��\n");
				jTextArea.append("������ѡ��ͼƬĿ¼");
			}
		});
	}
}  

//Test

