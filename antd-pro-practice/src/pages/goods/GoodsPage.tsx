import React, {useState, useEffect} from 'react';
import {Card, Table, Row, Col} from 'antd';
import {page, AdminGoodsPageReqVo, GoodsDO} from '@/services/goods';
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
  const [dataSource, setDataSource] = useState(new Array<GoodsDO>());
  const [dataTotal, setDataTotal] = useState(0);
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
      setDataSource(res.list);
      setDataTotal(res.total);
    });
  }

  return (
    <Card>
      <Row>
        <Col span={24}>

        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <Table rowKey="id" dataSource={dataSource} columns={columns}
                 pagination={{onChange: handPageChange, total: dataTotal , showSizeChanger: true}}/>
        </Col>
      </Row>
    </Card>
  );
};

export default GoodsPage;
