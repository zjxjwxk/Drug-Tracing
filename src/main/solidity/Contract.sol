pragma solidity ^0.4.0;

contract _manufacturer {    //药品生产商
    struct Manufacturer {    //生产商
        address manufacturerAddr;
        bytes manufacturerName;
    }
    mapping (address=>Manufacturer) manufacturer;
    
    struct Formulation {    //药方
        bytes drugID;
        bytes drugName;
        bytes[] material;
    }
    mapping (bytes=>Formulation) drug;
    
    bytes[] packageID;  //小包
    bytes[] boxID;      //大包
    mapping (bytes=>bytes[]) packing; //大包编号映射小包编号数组
    
    struct BoxInfo {   //药品生产信息
        bytes boxID;
        address manufacturerAddr;
        uint time;
        bytes[] materialID;
    }
    mapping (bytes=>BoxInfo) boxInfo;
    
    function gets & sets{...}   //生产商信息查询及维护,对象均以地址变量定位，set函数若地址下对象不存在则为其新建对象，set和get均返回执行结果bytes
    function pack(bytes packageID, bytes boxID) returns(bytes){}  //输入小包编码和大包编码，返回执行结果(所有函数未加说明的bytes变量返回值均为函数处理结果)
    function setFormulation(bytes drugID, bytes drugName, bytes[] material) returns(bytes){}
    function setBoxInfo(bytes boxID, address manufacturerAddr, uint tine, bytes[] materialID) returns(bytes){}
//    function getOrder(address manufacturerAddr) returns(bytes[],bytes){}  //输入生产商地址，返回链上所有该生产商的未处理订单编号
//    function takeOrder(address manufacturerAddr, uint time, address transporterAddr) returns(bytes){}
}

contract _transporter{
    struct Transporter{
        address transporterAddr;
        bytes transporterName;
    }
    mapping (address=>Transporter) transporter;
    
    struct TransportInfo{
        bytes boxID;
        address manufacturerAddr;
        address transporterAddr;
        address sellerAddr;
        uint pickTime;
        uint dropTime;
    }
    mapping (bytes=>TransportInfo) transportInfo;
    
    function gets & sets {...}
//    function getOrder(address transporterAddr) returns(bytes[],byte){};
    function pick(bytes boxID, uint time, bytes orderID) returns(bytes){};
    function drop(bytes boxID, uint time, bytes orderID) returns(bytes){};
}

contract _seller{
    struct Seller{
        address sellerAddr;
        bytes sellerName;
        uint sellerType;  //0-医院；1-药店；2-电商；
    }
    mapping (address=>Seller) seller;
    
    struct SellInfo{
        bytes packageID;
        uint time;
        address sellerAddr;
        address consumerAddr;
        uint price;
    }
    mapping (bytes=>SellInfo) sellerInfo;
    
    function gets & sets {...}
    function setSellInfo(bytes packageID, uint time, address sellerAddr, address consumerAddr, uint price) returns(bytes){}
//    function getBoxInfo(bytes drugType) returns(bytes[],byte){}  //输入药品名称（即大包的前缀），返回所有该在售药品的大包ID
//    function issueOrder(address sellerAddr, address manufacturerAddr, bytes boxID) returns(byte){}
}

contract _consumer{
    struct Consumer{
        address consumerAddr;
        bool gender;  //0-女, 1-男；
        uint age;
    }
    mapping (address=>Consumer) consumer;
    
    struct FeedBackInfo{
        address consumerAddr;
        uint time;
        bytes packageID;
        bytes information;
    }
    mapping (bytes=>FeedBackInfo) feedBackInfo;
    
    struct MedicineInfo{    //用于在内存中存储溯源信息
        bytes drugName;
        bytes[] material;
        bytes[] materialID;
        bytes manufacturerName;
        uint puckTime;
        bytes transporterName;
        uint dropTime;
        bytes sellerName;
    }
    
    function trace(bytes packageID) returns(MedicineInfo){}
    function feedBack(bytes packageID, address consumerAddr, uint time, bytes information) returns(bytes){}
}

contract _authority{
    address thisAuthority;
    
    struct MedicineInfo{    //用于在内存中存储溯源信息
        bytes drugName;
        bytes[] material;
        bytes[] materialID;
        bytes manufacturerName;
        uint puckTime;
        bytes transporterName;
        uint dropTime;
        bytes sellerName;
    }
    struct FeedBackInfo{
        uint gender;
        uint age;
        uint time;
        bytes packageID;
        bytes information;
    }
    
    function trace(bytes packageID) returns(MedicineInfo){}
    function getFeedBack() returns(FeedBackInfo){}
}