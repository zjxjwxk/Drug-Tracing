pragma solidity ^0.4.0;

contract MedicineSourceTracing {
    /*********************Manufacturer data structrue******************************/
    struct Manufacturer {    //生产商
        address manufacturerAddr;
        bytes manufacturerName;
    }
    mapping (address=>Manufacturer) manufacturer;
    
    struct Formulation {    //药方
        bytes3 drugID;
        bytes drugName;
        bytes material;
        uint existTag;
    }
    mapping (bytes3=>Formulation) formulation;
    
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
    struct Transporter{
        address transporterAddr;
        bytes transporterName;
    }
    mapping (address=>Transporter) transporter;
    
    struct TransportInfo{
        bytes6 boxID;
        address manufacturerAddr;
        address transporterAddr;
        address sellerAddr;
        uint pickTime;
        uint dropTime;
    }
    mapping (bytes6=>TransportInfo) transportInfo;
    
    /*************************seller data structrue**************************/ 
    struct Seller{
        address sellerAddr;
        bytes sellerName;
        uint sellerType;  //0-医院；1-药店；2-电商；
    }
    mapping (address=>Seller) seller;
    
    struct SellInfo{
        bytes9 packageID;
        uint time;
        address sellerAddr;
        address consumerAddr;
        uint price;
    }
    mapping (bytes9=>SellInfo) sellInfo;
    
    /*************************consumer data structrue**************************/
    struct Consumer{
        address consumerAddr;
        uint gender;  //0-女, 1-男；
        uint age;
    }
    mapping (address=>Consumer) consumer;
    
    struct FeedBackInfo{
        address consumerAddr;
        uint time;
        bytes9 packageID;
        bytes information;
    }
    mapping (bytes9=>FeedBackInfo) feedBackInfo;
    
    /**************************user information sets********************************************/
    event newManufacturer(address sender, string message);
    function setManufacturer(address manufacturerAddr, bytes manufacturerName) public {
        if(manufacturerAddr == address(0))
            manufacturerAddr = msg.sender;     //输入为0地址时，默认为当前用户地址
        
        bool tag;
        if(manufacturer[manufacturerAddr].manufacturerAddr == address(0))
            tag=false;
        else
            tag=true;
            
        manufacturer[manufacturerAddr].manufacturerAddr = manufacturerAddr;
        manufacturer[manufacturerAddr].manufacturerName = manufacturerName;
        
        if(tag == false)
            emit newManufacturer(msg.sender,  "用户注册成功");
        else
            emit newManufacturer(msg.sender,  "用户信息修改成功");
    }
    
    event newTransporter(address sender, string message);
    function setTransporter(address transporterAddr, bytes transporterName) public {
        if(transporterAddr == address(0))
            transporterAddr = msg.sender;     //输入为0地址时，默认为当前用户地址
        
        bool tag;
        if(transporter[transporterAddr].transporterAddr == address(0))
            tag=false;
        else
            tag=true;
            
        transporter[transporterAddr].transporterAddr = transporterAddr;
        transporter[transporterAddr].transporterName = transporterName;
        
        if(tag == false)
            emit newTransporter(msg.sender, "注册用户成功");
        else
            emit newTransporter(msg.sender, "用户信息修改成功");
    }
    
    event newSeller(address sender, string message);
    function setSeller(address sellerAddr, bytes sellerName, uint sellerType) public {
        if(sellerAddr == address(0))
            sellerAddr = msg.sender;     //输入为0地址时，默认为当前用户地址
        
        bool tag;
        if(seller[sellerAddr].sellerAddr == address(0))
            tag=false;
        else
            tag=true;
            
        seller[sellerAddr].sellerAddr = sellerAddr;
        seller[sellerAddr].sellerName = sellerName;
        seller[sellerAddr].sellerType = sellerType;
        
        if(tag == false)
            emit newSeller(msg.sender , "注册用户成功");
        else
            emit newSeller(msg.sender , "用户信息修改成功");
    }
    
    event newConsumer(address sender, string message);
    function setConsumer(address consumerAddr, uint gender, uint age) public {
        if(consumerAddr == address(0))
            consumerAddr = msg.sender;     //输入为0地址时，默认为当前用户地址
        
        bool tag;
        if(consumer[consumerAddr].consumerAddr == address(0))
            tag=false;
        else
            tag=true;
            
        consumer[consumerAddr].consumerAddr = consumerAddr;
        consumer[consumerAddr].gender = gender;
        consumer[consumerAddr].age = age;
        
        if(tag == false)
            emit newConsumer(msg.sender , "注册用户成功");
        else
            emit newConsumer(msg.sender , "用户信息修改成功");
    }    
    
    /**************************functions********************************************/
    /*****************by manufacturer****************/
    event newPackInfo(address sender, bool isSuccess, string message);
    function pack(bytes9 packageID, bytes6 boxID) public {
        if(boxInfo[boxID].time == 0)
            emit newPackInfo(msg.sender, false, "大包信息不存在，请重新输入");
        for(uint i=0; i<6; i++)
            if(packageID[i] != boxID[i])
                emit newPackInfo(msg.sender, false, "信息输入有误，请重新输入");
        packing[boxID].push(packageID);
        emit newPackInfo(msg.sender, true, "包装信息上传成功");
    }
    
    event newFormulation(address sender, string message);
    function setFormulation(bytes3 drugID, bytes drugName, bytes material) public {
        bool tag;
        if(formulation[drugID].existTag == 0)
            tag = false;
        else
            tag = true;

        formulation[drugID].drugID = drugID;
        formulation[drugID].drugName = drugName;
        formulation[drugID].material = material;
        formulation[drugID].existTag = 1;
        
        if(tag == false)
            emit newFormulation(msg.sender,"新配方上传成功");
        else
            emit newFormulation(msg.sender,"配方已存在，修改成功");
    }
    
    event newBoxInfo(address sender, string message);
    function setBoxInfo(bytes6 boxID, address manufacturerAddr, uint time, bytes materialID) public {
        if(manufacturerAddr == address(0)) 
            manufacturerAddr=msg.sender;          //传入空地址时默认为合约调用者地址
        if(time == 0) 
            time=now;                             //传入0时默认为当前时间
        
        bool tag;
        if(boxInfo[boxID].time == 0)
            tag=false;
        else
            tag=true;

        boxInfo[boxID].boxID = boxID;
        boxInfo[boxID].manufacturerAddr = manufacturerAddr;
        boxInfo[boxID].time = time;
        boxInfo[boxID].materialID = materialID;
        
        if(tag == false)
            emit newBoxInfo(msg.sender, "药品生产信息上传成功");
        else
            emit newBoxInfo(msg.sender, "药品信息已存在，信息更新成功");
    }
    
    /*****************by transporter****************/
    event newPickInfo(address sender, string message);
    function pick(bytes6 boxID, uint time) public {
        
        if(time == 0) 
            time = now;    //时间输入为0时默认当前时间
            
        transportInfo[boxID].boxID = boxID;
        transportInfo[boxID].manufacturerAddr = boxInfo[boxID].manufacturerAddr;
        transportInfo[boxID].transporterAddr = msg.sender;
        transportInfo[boxID].pickTime = time;

        emit newPickInfo(msg.sender, "取货成功");
    }
    
    event newDropInfo(address sender, string message);
    function drop(bytes6 boxID, uint time,address sellerAddr) public {
        if(time == 0) 
            time = now;    //时间输入为0时默认当前时间
            
        transportInfo[boxID].dropTime = time;
        transportInfo[boxID].sellerAddr = sellerAddr;

        emit newDropInfo(msg.sender, "送达成功");
    }
    
    /*****************by seller****************/
    event newSellInfo(address sender, string message);
    function setSellInfo(bytes9 packageID, uint time, address sellerAddr, address consumerAddr, uint price) public {
        if(time == 0) 
            time = now;    //时间输入为0时默认当前时间
        if(sellerAddr == address(0))
            sellerAddr = msg.sender;   //输入地址为空地址，则默认为当前用户地址
        
        sellInfo[packageID].packageID = packageID;
        sellInfo[packageID].time = time;
        sellInfo[packageID].sellerAddr = sellerAddr;
        sellInfo[packageID].consumerAddr = consumerAddr;
        sellInfo[packageID].price = price;

        emit newSellInfo(msg.sender, "零售信息上传成功");
    }
    
     /*****************by consumer or authority****************/
     function trace(bytes9 packageID) public view returns(
        bytes drugName,
        bytes material,
        bytes materialID,
        bytes manufacturerName,
        uint pickTime,
        bytes transporterName,
        uint dropTime,
        bytes sellerName){
            
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
    event newFeedBack(address sender, string message);
    function feedBack(bytes9 packageID, address consumerAddr, uint time, bytes information) public {
        if(time == 0) 
            time = now;    //时间输入为0时默认当前时间
        if(consumerAddr == address(0))
            consumerAddr = msg.sender;   //输入地址为空地址，则默认为当前用户地址
            
        feedBackInfo[packageID].packageID = packageID;
        feedBackInfo[packageID].consumerAddr = consumerAddr;
        feedBackInfo[packageID].time = time;
        feedBackInfo[packageID].information = information;

        emit newFeedBack(msg.sender,"信息反馈成功");
    }
}