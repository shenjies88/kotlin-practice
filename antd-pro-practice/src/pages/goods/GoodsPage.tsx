import React, {SyntheticEvent, useEffect, useState} from 'react';
import {Button, Card, Col, Divider, Input, Row, Table} from 'antd';
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
  const [searchParam, setSearchParam] = useState(new AdminGoodsPageReqVo());

  useEffect(() => {
    queryList();
  }, []);

  function handPageChange(page: number, pageSize?: number) {
    searchParam.num = page;
    searchParam.size = pageSize || 10;
    setSearchParam(searchParam);
    queryList();
  }

  function queryList() {
    page(searchParam).then(res => {
      setDataSource(res);
    });
  }

  function onInputChange(e: SyntheticEvent) {
    searchParam.userId = Number.parseInt(e.target.value);
    setSearchParam(searchParam);
  }

  return (
    <Card>
      <Row>
        <Col span={3}>
          <Input addonBefore="用户id" allowClear={true} value={searchParam.userId} onChange={onInputChange}/>
        </Col>
        <Col span={19}/>
        <Col span={2}>
          <Button type="primary" onClick={queryList}>查询</Button>
        </Col>
      </Row>
      <Divider orientation="left"/>
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
