import React, {useEffect, useState} from 'react';
import {Card, Col, Row, Table} from 'antd';
import {ColumnsType} from "antd/es/table";
import {AdminUserPageReqVo, page, UserDO} from '@/services/user';

const columns: ColumnsType<UserDO> = [
  {
    title: 'id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '账号',
    dataIndex: 'account',
    key: 'account',
  },
  {
    title: '昵称',
    dataIndex: 'nickname',
    key: 'nickname',
  },
];

const UserPage: React.FC = () => {
  const [dataSource, setDataSource] = useState(new Array<UserDO>());
  const [dataTotal, setDataTotal] = useState(0);
  const [pageParam, setPageParam] = useState(new AdminUserPageReqVo());

  useEffect(() => {
    queryList();
  }, []);

  function handPageChange(page: number, pageSize?: number) {
    pageParam.num = page;
    pageParam.size = pageSize || 10;
    setPageParam(pageParam);
    queryList();
  }

  function queryList() {
    page(pageParam).then(res => {
      setDataSource(res.list);
      setDataTotal(res.total);
    });
  }

  return (
    <Card>
      <Row>
        <Col span={24}>
          查询
        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <Table rowKey="id" dataSource={dataSource} columns={columns}
                 pagination={{onChange: handPageChange, total: dataTotal, showSizeChanger: true}}/>
        </Col>
      </Row>
    </Card>
  );
};

export default UserPage;
