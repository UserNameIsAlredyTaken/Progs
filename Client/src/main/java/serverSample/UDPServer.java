package serverSample;

import ORM.*;
import io.dataBaseInteraction.DataBaseInteraction;
import items.FoodResidus;
import items.TestClassToInsert;
import serealize.XMLworker;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.net.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

class UDPServer{
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Musorka";
    private static final String USER = "postgres";
    private static final String PASSSWORD = "z2UjMkxX";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String NAME = "public.\"FoodResidus\"";
    private static ArrayList<InetAddress> currentClientsIPs= new ArrayList();
    private static ArrayList<Integer> currentClientsPorts= new ArrayList();
    public static void main(String args[]) throws Exception{
        DataBaseCommunication dbc = new DataBaseCommunication(URL, USER, PASSSWORD, DRIVER);
        Statement statement = dbc.getStatement();
        Queries queries = new Queries();
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[4096];
        byte[] sendData;
        System.out.print("Server is ready: ");
        System.out.println(InetAddress.getLocalHost());
        while(true){//TODO обнавлять бд на открытие пользователем xml файла
            DatagramPacket receiveCommand = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receiveCommand);
            InetAddress IPAddress = receiveCommand.getAddress();
            int port = receiveCommand.getPort();
            if(!currentClientsIPs.contains(IPAddress)){
                currentClientsIPs.add(IPAddress);
                currentClientsPorts.add(port);
            }
            System.out.println(IPAddress.getHostName()+" was connected");
            if(receiveData[0]== DataBaseInteraction.INIT_TABLE){


                /*//TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet = testRsFactory.createJdbcRowSet();
                FoodResidus testFood = new FoodResidus();
                testFood.name="testName";
                testFood.wheight=111;
                INCERT cr=new INCERT();
                cr.insertRow(testJdbcRowSet,testFood,statement);
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE*/

                /*//TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory2 = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet2 = testRsFactory2.createJdbcRowSet();
                TestClassToInsert testClassToInsert = new TestClassToInsert();
                CREATE_AND_INCERT<TestClassToInsert> cai=new CREATE_AND_INCERT();
                cai.create(testJdbcRowSet2,testClassToInsert,statement);
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE*/

                /*//TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory3 = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet3 = testRsFactory3.createJdbcRowSet();
                TestClassToInsert testClassToInsert2 = new TestClassToInsert();
                SELECT sel=new SELECT();
                sel.selectRowsTheSameType(testJdbcRowSet3,testClassToInsert2,statement);
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE*/

                /*//TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory4 = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet4 = testRsFactory4.createJdbcRowSet();
                FoodResidus oldTestFood = new FoodResidus();
                oldTestFood.name="NeTestName";
                oldTestFood.wheight=222;
                FoodResidus newTestFood = new FoodResidus();
                newTestFood.name="testName";
                newTestFood.wheight=111;
                UPDATE up = new UPDATE();
                up.updateRow(testJdbcRowSet4,oldTestFood,newTestFood,statement);
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE*/

                /*//TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory5 = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet5 = testRsFactory5.createJdbcRowSet();
                TestClassToInsert testClassToInsert2 = new TestClassToInsert();
                FoodResidus oldTestFood = new FoodResidus();
                oldTestFood.name="testName";
                oldTestFood.wheight=111;
                DELETE del = new DELETE();
                del.deleteRow(testJdbcRowSet5,oldTestFood,statement);
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE*/

                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE
                RowSetFactory testRsFactory6 = RowSetProvider.newFactory();
                JdbcRowSet testJdbcRowSet6 = testRsFactory6.createJdbcRowSet();
                HashSet<FoodResidus> list = new HashSet<>();
                SELECT sel = new SELECT();
                list=sel.getAllObj(FoodResidus.class,statement);
                System.out.println(list.size());
                //TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE TEST CODE

                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                String str = XMLworker.objectToXML(data);
                sendData=str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }else if(receiveData[0]== DataBaseInteraction.CLEAR_TABLE){
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                queries.removeAllRows(jdbcRowSet, NAME,  dbc.getPooledConnection().getConnection());
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.CLEAR_TABLE;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.CHANGE_ELEMENT) {
                byte[] receiveOldByte = new byte[4096];
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveOld = new DatagramPacket(receiveOldByte, receiveData.length);
                serverSocket.receive(receiveOld);
                String oldXml=new String(receiveOldByte);
                HashSet oldObject=XMLworker.xmlToObject(oldXml);
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.replaceRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), oldObject, newObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.ADD_ELEMENT){
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.insertRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), newObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.ADD_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.REMOVE_ELEMENT){
                byte[] receiveOldByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveOld = new DatagramPacket(receiveOldByte, receiveData.length);
                serverSocket.receive(receiveOld);
                String newXml=new String(receiveOldByte);
                HashSet oldObject=XMLworker.xmlToObject(newXml);
                queries.deleteRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), oldObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        //System.out.println(currentClientsIPs.get(i));
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.REMOVE_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.REFRESH_TABLE){
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.refreshTable(jdbcRowSet, NAME, newObject, dbc.getPooledConnection().getConnection());
                for(int i=0; i<currentClientsIPs.size(); i++){//TODO занести в отдельный метод обратную отсылку данных из бд
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.REFRESH_TABLE;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }
        }
    }
}