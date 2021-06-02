package com.snailwu.mybatis;

import com.snailwu.mybatis.dao.NpsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author WuQinglong
 * @date 2021/6/2 1:43 下午
 */
public class PrintSqlMain {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        NpsMapper npsMapper = sqlSession.getMapper(NpsMapper.class);

        // 验证 分校-学部视角
        npsMapper.npsDiff(81, 82, "010", "0", "50", "department");
        // 验证 分校-课态视角
        npsMapper.npsDiff(81, 82, "010", "0", "50", "classForm");
        // 验证 分校-选择学部（视角无效）
        npsMapper.npsDiff(81, 82, "010", "0", "400", "");
        // 验证 分校-选择课态（视角无效）
        npsMapper.npsDiff(81, 82, "010", "2", "50", "");
        // 验证 分校-选择学部+课态（视角无效）
        npsMapper.npsDiff(81, 82, "010", "2", "400", "");
        System.out.println("--------------------------");

        // 验证 全国-分校视角
        npsMapper.npsDiff(81, 82, "T0", "0", "50", "city");
        // 验证 全国-学部视角
        npsMapper.npsDiff(81, 82, "T0", "0", "50", "department");
        // 验证 全国-课态视角
        npsMapper.npsDiff(81, 82, "T0", "0", "50", "classForm");
        // 验证 全国-选择学部-分校视角
        npsMapper.npsDiff(81, 82, "T0", "0", "400", "city");
        // 验证 全国-选择学部-课态视角
        npsMapper.npsDiff(81, 82, "T0", "0", "400", "classForm");
        // 验证 全国-选择课态-分校视角
        npsMapper.npsDiff(81, 82, "T0", "2", "50", "city");
        // 验证 全国-选择课态-学部视角
        npsMapper.npsDiff(81, 82, "T0", "2", "50", "department");
        // 验证 全国-选择学部+课态
        npsMapper.npsDiff(81, 82, "T0", "2", "400", "");
        System.out.println("--------------------------");

        // 验证 梯次-分校视角
        npsMapper.npsDiff(81, 82, "T1", "0", "50", "city");
        // 验证 梯次-学部视角
        npsMapper.npsDiff(81, 82, "T1", "0", "50", "department");
        // 验证 梯次-课态视角
        npsMapper.npsDiff(81, 82, "T1", "0", "50", "classForm");
        // 验证 梯次-选择学部-分校视角
        npsMapper.npsDiff(81, 82, "T1", "0", "400", "city");
        // 验证 梯次-选择学部-课态视角
        npsMapper.npsDiff(81, 82, "T1", "0", "400", "classForm");
        // 验证 梯次-选择课态-分校视角
        npsMapper.npsDiff(81, 82, "T1", "2", "50", "city");
        // 验证 梯次-选择课态-学部视角
        npsMapper.npsDiff(81, 82, "T1", "2", "50", "department");
        // 验证 梯次-选择学部+课态
        npsMapper.npsDiff(81, 82, "T1", "2", "400", "");

    }

}
