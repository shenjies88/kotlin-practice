import React, {useEffect, useState} from 'react';
import {Card, Col, Row, Table} from 'antd';
import {AdminGoodsPageReqVo, GoodsDO, page} from '@/services/goods';
import {ColumnsType} from "antd/es/table";

const columns: ColumnsType<GoodsDO> = [
  {
    title: '主键',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '用户id',
    dataIndex: 'userId',
    key: 'userId',
  },
  {
    title: '产品名称',
    dataIndex: 'name',
    key: 'name',
  },
];

const GoodsPage: React.FC = () => {
  const [dataSource, setDataSource] = useState({list: new Array<GoodsDO>(), total: 0});
  const [pageParam, setPageParam] = useState(new AdminGoodsPageReqVo());

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
      setDataSource(res);
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
          <Table rowKey="id" dataSource={dataSource.list} columns={columns}
                 pagination={{onChange: handPageChange, total: dataSource.total, showSizeChanger: true}}/>
        </Col>
      </Row>
    </Card>
  );
};

export default GoodsPage;
