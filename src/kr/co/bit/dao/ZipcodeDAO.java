package kr.co.bit.dao;

import kr.co.bit.connectionmanager.ConnectionManager;
import kr.co.bit.vo.ZipcodeVO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipcodeDAO {

    public List<ZipcodeVO> search(String dong) {
        ArrayList<ZipcodeVO> list = new ArrayList<>();
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();
        String sql = "select * from ZIPCODE_TBL where DONG like ?";
        PreparedStatement preparedStatement = null;
        ZipcodeVO vo;
        ResultSet resultSet= null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,"%"+dong+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vo = new ZipcodeVO();
                vo.setZipcode(resultSet.getString(1));
                vo.setSido(resultSet.getString(2));
                vo.setGugun(resultSet.getString(3));
                vo.setDong(resultSet.getString(4));
                vo.setRi(resultSet.getString(5));
                vo.setBldg(resultSet.getString(6));
                vo.setBunji(resultSet.getString(7));
                vo.setSeq(resultSet.getString(8));

                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mgr.connectClose(con,preparedStatement,resultSet);
        }

        return list;
    }

    private ZipcodeVO getInstance(String line) {
        String[] temp = line.split(",");
        ZipcodeVO vo = new ZipcodeVO();
        vo.setZipcode(temp[0]);
        vo.setSido(temp[1]);
        vo.setGugun(temp[2].equals("") ? " " : temp[2]);
        vo.setDong(temp[3]);
        vo.setRi(temp[4].equals("") ? " " : temp[4]);
        vo.setBldg(temp[5].equals("") ? " " : temp[5]);
        vo.setBunji(temp[6].equals("") ? " " : temp[6]);
        vo.setSeq(temp[7]);
        return vo;
    }

    public boolean insert(String path) {
        List<ZipcodeVO> list = new ArrayList<>();
        boolean flag = false;
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();

        File file = new File(path);

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                list.add(this.getInstance(line));
            }
            bufferedReader.close();
            fileReader.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ZIPCODE_TBL VALUES (?,?,?,?,?,?,?,?)";
        try {

            preparedStatement = con.prepareStatement(sql);
            for (ZipcodeVO vo : list) {

                preparedStatement.setString(1, vo.getZipcode());
                preparedStatement.setString(2, vo.getSido());
                preparedStatement.setString(3, vo.getGugun());
                preparedStatement.setString(4, vo.getDong());
                preparedStatement.setString(5, vo.getRi());
                preparedStatement.setString(6, vo.getBldg());
                preparedStatement.setString(7, vo.getBunji());
                preparedStatement.setString(8, vo.getSeq());

                int affectCount = preparedStatement.executeUpdate(); //영향을 받은 횟수
                if (affectCount > 0) {
                    flag = true;
                    System.out.println("삽입완료");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        mgr.connectClose(con, null, null);
        return flag;
    }
}
