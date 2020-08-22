pragma solidity ^0.4.0;

contract MedicineSourceTracing {
    /*********************Manufacturer data structrue******************************/
    struct Manufacturer {    //生产商
        address manufacturerAddr;
        string manufacturerName;
        uint existTag;
    }
    mapping (address=>Manufacturer) manufacturer;
    address[] manufacturers;
    
    struct Formulation {    //药方
        bytes3 drugID;
        string drugName;
        string material;
        uint existTag;
    }
    mapping (bytes3=>Formulation) formulation;
    bytes3[] formulations;
    
    mapping (bytes6=>bytes9[]) packing; //大包编号映射小包编号数组
                                        //小包编号：3byte药品ID+3byte大包ID+3byte小包ID
                                        //大包编号：3byte药品ID+3byte大包ID
    
    struct BoxInfo {   //药品生产信息
        bytes6 boxID;
        address manufacturerAddr;
        uint time;
        bytes materialID;
    }
    mapping (bytes6=>BoxInfo) boxInfo;
    
    /************************transporter data structrue***************************/  
    struct Transporter {
        address transporterAddr;
        string transporterName;
        uint existTag;
    }
    mapping (address=>Transporter) transporter;
    address[] transporters;
    
    struct TransportInfo {
        bytes6 boxID;
        address manufacturerAddr;
        address transporterAddr;
        address sellerAddr;
        uint pickTime;
        uint dropTime;
    }
    mapping (bytes6=>TransportInfo) transportInfo;
    
    /*************************seller data structrue**************************/ 
    struct Seller {
        address sellerAddr;
        string sellerName;
        uint sellerType;  //0-医院；1-药店；2-电商；
        uint existTag;
    }
    mapping (address=>Seller) seller;
    address[] sellers;
    
    struct SellInfo {
        bytes9 packageID;
        uint time;
        address sellerAddr;
        address consumerAddr;
        uint price;
    }
    mapping (bytes9=>SellInfo) sellInfo;
    
    /*************************consumer data structrue**************************/
    struct Consumer {
        address consumerAddr;
        uint gender;  //0-女, 1-男；
        uint age;
        uint existTag;
    }
    mapping (address=>Consumer) consumer;
    
    struct FeedBackInfo {
        address consumerAddr;
        uint time;
        bytes9 packageID;
        string information;
    }
    mapping (bytes9=>FeedBackInfo) feedBackInfo;
    mapping (bytes3=>bytes9[]) feedBackInfo_drug;
    
    /*************************authority data structrue & set function**************************/
    address authority;          //监管部门唯一，以地址标识
    event newAuthority(address sender, string message);
    function setAuthority(address authorityAddr) public {
        authority = authorityAddr;
        emit newAuthority(msg.sender, "监管部门设置成功");
    }
    
    /**************************user information sets********************************************/
    event newManufacturer(address sender, string message);
    function setManufacturer(string manufacturerName) public {
        bool tag;
        if (manufacturer[msg.sender].manufacturerAddr == address(0))
            tag = false;
        else
            tag = true;
            
        manufacturer[msg.sender].manufacturerAddr = msg.sender;
        manufacturer[msg.sender].manufacturerName = manufacturerName;
        manufacturer[msg.sender].existTag = 1;
        
        if (tag == false) {
            emit newManufacturer(msg.sender, "用户注册成功");
            manufacturers.push(msg.sender);
        } else
            emit newManufacturer(msg.sender, "用户信息修改成功");
    }
    
    event newTransporter(address sender, string message);
    function setTransporter(string transporterName) public {
        bool tag;
        if (transporter[msg.sender].transporterAddr == address(0))
            tag = false;
        else
            tag = true;
            
        transporter[msg.sender].transporterAddr = msg.sender;
        transporter[msg.sender].transporterName = transporterName;
        transporter[msg.sender].existTag = 1;
        
        if (tag == false) {
            emit newTransporter(msg.sender, "注册用户成功");
            transporters.push(msg.sender);
        } else
            emit newTransporter(msg.sender, "用户信息修改成功");
    }
    
    event newSeller(address sender, string message);
    function setSeller(string sellerName, uint sellerType) public {
        bool tag;
        if (seller[msg.sender].sellerAddr == address(0))
            tag = false;
        else
            tag = true;
            
        seller[msg.sender].sellerAddr = msg.sender;
        seller[msg.sender].sellerName = sellerName;
        seller[msg.sender].sellerType = sellerType;
        seller[msg.sender].existTag = 1;
        
        if (tag == false) {
            emit newSeller(msg.sender, "注册用户成功");
            sellers.push(msg.sender);
        } else
            emit newSeller(msg.sender, "用户信息修改成功");
    }
    
    event newConsumer(address sender, string message);
    function setConsumer(uint gender, uint age) public {
        bool tag;
        if (consumer[msg.sender].consumerAddr == address(0))
            tag = false;
        else
            tag = true;
            
        consumer[msg.sender].consumerAddr = msg.sender;
        consumer[msg.sender].gender = gender;
        consumer[msg.sender].age = age;
        consumer[msg.sender].existTag = 1;
        
        if (tag == false)
            emit newConsumer(msg.sender, "注册用户成功");
        else
            emit newConsumer(msg.sender, "用户信息修改成功");
    }    
    
    /**************************functions********************************************/
    /*****************by manufacturer****************/
    event newPackInfo(address sender, bool isSuccess, string message);
    function pack(bytes9 packageID, bytes6 boxID) public {
        if (manufacturer[msg.sender].existTag == 0) {
            emit newPackInfo(msg.sender, false, "当前用户无包装信息上传权限");
            return;
        }
        if (boxInfo[boxID].time == 0) {
            emit newPackInfo(msg.sender, false, "大包信息不存在，请重新输入");
            return;
        }
        for (uint i = 0; i < 6; i++)
            if (packageID[i] != boxID[i]) {
                emit newPackInfo(msg.sender, false, "信息输入有误，请重新输入");
                return;
            }
        packing[boxID].push(packageID);
        emit newPackInfo(msg.sender, true, "包装信息上传成功");
    }
    
    event newFormulation(address sender, bool isSuccess, string message);
    function setFormulation(bytes3 drugID, string drugName, string material) public {
        if (manufacturer[msg.sender].existTag == 0) {
            emit newFormulation(msg.sender, false, "当前用户无上传配方权限");
            return;
        }
        bool tag;
        if (formulation[drugID].existTag == 0)
            tag = false;
        else
            tag = true;

        formulation[drugID].drugID = drugID;
        formulation[drugID].drugName = drugName;
        formulation[drugID].material = material;
        formulation[drugID].existTag = 1;
        
        if (tag == false) {
            emit newFormulation(msg.sender, true, "新配方上传成功");
            formulations.push(drugID);
        } else
            emit newFormulation(msg.sender, true, "配方已存在，修改成功");
    }
    
    event newBoxInfo(address sender, bool isSuccess, string message);
    function setBoxInfo(bytes6 boxID, uint time, bytes materialID) public {
        if (manufacturer[msg.sender].existTag == 0) {
            emit newBoxInfo(msg.sender, false, "当前用户无上传药品生产信息权限");
            return;
        }
        if (time == 0) 
            time = now;                             //传入0时默认为当前时间
        
        bool tag;
        if (boxInfo[boxID].time == 0)
            tag = false;
        else
            tag = true;

        boxInfo[boxID].boxID = boxID;
        boxInfo[boxID].manufacturerAddr = msg.sender;
        boxInfo[boxID].time = time;
        boxInfo[boxID].materialID = materialID;
        
        if (tag == false)
            emit newBoxInfo(msg.sender, true, "药品生产信息上传成功");
        else
            emit newBoxInfo(msg.sender, true, "药品信息已存在，信息更新成功");
    }
    
    /*****************by transporter****************/
    event newPickInfo(address sender, bool isSuccess, string message);
    function pick(bytes6 boxID, uint time) public {
        if (transporter[msg.sender].existTag == 0) {
            emit newPickInfo(msg.sender, false, "当前用户无上传取货信息权限");
            return;
        }
        if (boxInfo[boxID].time == 0) {
            emit newPickInfo(msg.sender, false, "药品大包信息不存在，无法取货");
            return;
        }
        if (time == 0) 
            time = now;    //时间输入为0时默认当前时间
            
        transportInfo[boxID].boxID = boxID;
        transportInfo[boxID].manufacturerAddr = boxInfo[boxID].manufacturerAddr;
        transportInfo[boxID].transporterAddr = msg.sender;
        transportInfo[boxID].pickTime = time;

        emit newPickInfo(msg.sender, true, "取货成功");
    }
    
    event newDropInfo(address sender, bool isSuccess, string message);
    function drop(bytes6 boxID, uint time, address sellerAddr) public {
        if (transporter[msg.sender].existTag == 0) {
            emit newDropInfo(msg.sender, false, "当前用户无送达权限");
            return;
        }
        if (transportInfo[boxID].pickTime == 0 || transportInfo[boxID].transporterAddr != msg.sender) {
            emit newDropInfo(msg.sender, false, "取货信息错误（大包未取货或运输企业地址错误），送达失败");
            return;
        }
        if (transportInfo[boxID].dropTime != 0) {
            emit newDropInfo(msg.sender, false, "药品已送达，无法重复送达");
            return;
        }
        if (seller[sellerAddr].existTag == 0) {
            emit newDropInfo(msg.sender, false, "销售平台不存在，上传信息失败");
            return;
        }
        if (time == 0) 
            time = now;    //时间输入为0时默认当前时间
            
        transportInfo[boxID].dropTime = time;
        transportInfo[boxID].sellerAddr = sellerAddr;

        emit newDropInfo(msg.sender, true, "送达成功");
    }
    
    /*****************by seller****************/
    event newSellInfo(address sender, bool isSuccess, string message);
    function setSellInfo(bytes9 packageID, uint time, address consumerAddr, uint price) public {
        if (seller[msg.sender].existTag == 0) {
            emit newSellInfo(msg.sender, false, "当前用户无零售信息上传权限");
            return;
        }
        if (consumer[consumerAddr].existTag == 0) {
            emit newSellInfo(msg.sender, false, "消费者信息不存在，零售信息上传失败");
            return;
        }
        bytes6 boxID = bytes6(packageID);
        if (transportInfo[boxID].sellerAddr != msg.sender) {
            emit newSellInfo(msg.sender, false, "药品信息错误，零售信息上传失败");
            return;
        }
        if (time == 0) 
            time = now;    //时间输入为0时默认当前时间
        
        sellInfo[packageID].packageID = packageID;
        sellInfo[packageID].time = time;
        sellInfo[packageID].sellerAddr = msg.sender;
        sellInfo[packageID].consumerAddr = consumerAddr;
        sellInfo[packageID].price = price;

        emit newSellInfo(msg.sender, true, "零售信息上传成功");
    }
    
     /*****************by consumer or authority****************/
     function trace(bytes9 packageID) public view returns (       //trace information by package   //by consumer or authority
        string drugName,
        string material,
        bytes materialID,
        string manufacturerName,
        uint pickTime,
        string transporterName,
        uint dropTime,
        string sellerName) {
            
        bytes3 drugID = bytes3(packageID);
        bytes6 boxID = bytes6(packageID);
        address temp;
        
        drugName = formulation[drugID].drugName;
        material = formulation[drugID].material;
        materialID = boxInfo[boxID].materialID;
            
        temp = boxInfo[boxID].manufacturerAddr;
        manufacturerName = manufacturer[temp].manufacturerName;
        
        pickTime = transportInfo[boxID].pickTime;
        temp = transportInfo[boxID].transporterAddr;
        transporterName = transporter[temp].transporterName;
        dropTime = transportInfo[boxID].dropTime;
        
        temp = sellInfo[packageID].sellerAddr;
        sellerName = seller[temp].sellerName;
    }

    event newFeedBack(address sender,bool isSuccess, string message);
    function feedBack(bytes9 packageID, uint time, string information) public {  //by consumer
        if (sellInfo[packageID].consumerAddr != msg.sender) {
            emit newFeedBack(msg.sender, false, "零售信息错误，反馈信息上传失败");
            return;
        }
        if (time == 0) 
            time = now;    //时间输入为0时默认当前时间
            
        feedBackInfo[packageID].packageID = packageID;
        feedBackInfo[packageID].consumerAddr = msg.sender;
        feedBackInfo[packageID].time = time;
        feedBackInfo[packageID].information = information;

        bytes3 drugID = bytes3(packageID);
        feedBackInfo_drug[drugID].push(packageID);

        emit newFeedBack(msg.sender, true, "信息反馈成功");
    }
    
    /*****************by authority only****************/
    modifier onlyAuthority {
        if (msg.sender != authority)
            revert();
        _;
    }
    function getManufacturers(uint index) onlyAuthority public view returns (
        address manufacturerAddr,
        string manufacturerName,
        bool isSuccess) {

        if (index >= manufacturers.length) {
            isSuccess = false;
            return;
        }
        manufacturerAddr = manufacturers[index];
        manufacturerName = manufacturer[manufacturers[index]].manufacturerName;
        isSuccess = true;
    }
    
    function getFormulations(uint index) onlyAuthority public view returns (
        bytes3 drugID,
        string drugName,
        string material,
        bool isSuccess) {

        if (index >= formulations.length) {
            isSuccess = false;
            return;
        }
        drugID = formulations[index];
        drugName = formulation[formulations[index]].drugName;
        material = formulation[formulations[index]].material;
        isSuccess = true;
    }
        
    function getTransporters(uint index) onlyAuthority public view returns (
        address transporterAddr,
        string transporterName,
        bool isSuccess) {

        if (index >= transporters.length) {
            isSuccess = false;
            return;
        }
        transporterAddr = transporters[index];
        transporterName = transporter[transporters[index]].transporterName;
        isSuccess = true;
    }
    
    function getSellers(uint index) onlyAuthority public view returns (
        address sellerAddr,
        string sellerName,
        uint sellerType,
        bool isSuccess) {

        if (index >= sellers.length) {
            isSuccess = false;
            return;
        }
        sellerAddr = sellers[index];
        sellerName = seller[sellers[index]].sellerName;
        sellerType = seller[sellers[index]].sellerType;
        isSuccess = true;
    }
    
    function getPackInfo(bytes6 boxID, uint index) onlyAuthority public view returns (
        bytes9 packageID,
        bool isSuccess) {

        if (index >= packing[boxID].length) {
            isSuccess = false;
            return;
        }
        packageID = packing[boxID][index];
        isSuccess = true;
    }
        
    function getFeedBacks(bytes3 drugID, uint index) onlyAuthority public view returns (
        bytes9 packageID,
        string information,
        uint age,
        uint gender,
        uint time,
        bool isSuccess) {

        if (index >= feedBackInfo_drug[drugID].length) {
            isSuccess = false;
            return;
        }
        address temp;
        packageID = feedBackInfo_drug[drugID][index];
        information = feedBackInfo[feedBackInfo_drug[drugID][index]].information;
        time = feedBackInfo[feedBackInfo_drug[drugID][index]].time;

        temp = feedBackInfo[feedBackInfo_drug[drugID][index]].consumerAddr;
        age = consumer[temp].age;
        gender = consumer[temp].gender;
        isSuccess = true;
    }
}