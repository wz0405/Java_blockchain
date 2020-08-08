var {Component} = React;
var {Router, Route, IndexRoute, Link} = ReactRouter;
 
class Main extends Component{
    render(){
 return(            
            <div>
                <h1>BlockChain Study</h1>
                <ul className="header" >
                    <li><Link exact to="/">Home</Link></li>
                    <li><Link to="/bitcoin">Bitcoin</Link></li>
                    <li><Link to="/ethereum">Ethereum</Link></li>
                    <li><Link to="/hyperledger">Hyperledger</Link></li>
                </ul>
                <div className="content">
                {this.props.children}
                </div>
            </div>            
        );
    }
}
class Home extends Component{
    render(){
        return(
            <div>
                <h2>HELLO</h2>
                <p>안녕하세요? BlockChain Java 웹 예제(dApp)입니다. 잘해보죠~!!!</p>
            </div>
        );
    }
}
    class BitcoinNetwork extends Component{
    state={
        blockNumber:0,
        acc1_balance:0,
        acc2_balance:0
    }
    
    bit_network_connect=()=>{
        axios.get('/bit_network/connect')
        .then((response)=>{
 console.log(response);
            this.setState({
                blockNumber:response.data.blockNumber,
                acc1_balance:response.data.acc1_balance,
                acc2_balance:response.data.acc2_balance
            });
            
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    send=()=>{
        alert(this.amount.value);
    axios.post('/bit_network/send',{"amount":this.amount.value})
        .then((response)=>{
            console.log(response);
            this.setState({
                blockNumber:response.data.blockNumber,
                acc1_balance:response.data.acc1_balance,
                acc2_balance:response.data.acc2_balance
            });
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    render(){
        return(
            <div>
                <h2>BitcoinNetwork</h2>
                <p><button onClick={this.bit_network_connect}>연결</button></p>
                <br/>
                block number : {this.state.blockNumber} <br/>
                acc1 balance : {this.state.acc1_balance} <br/>
                acc2 balance : {this.state.acc2_balance} <br/>
                <br/>
                <div>acc1가 acc2에게 {' '}
                <input placeholder='송금량' ref={ref=>this.amount=ref} />BTC  {' '} 
                <button onClick={this.send}  > 보내기</button><br/>               
                </div>
            </div>
        );
    }
}


/* 이더리움 연동 요청  */
class EthereumNetwork extends Component{  
	login=(event)=>{
	window.open("login.html",'_blank','width=600,height=350');
	}

	createUser=(event)=>{
	window.open("createUser.html",'_blank','width=800,height=550');
       
    }	
    
	send1=(event)=>{
        alert(this.state.name);
        event.preventDefault();
        axios.post('main',{"name":this.state.name,"sign":this.state.team})
        .then((response)=>{
            console.log(response);
            this.setState({
                result1:response.data.result,
                message1:response.data.message,                
            });
			
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    send2=(event)=>{
     alert(this.state.name);
        event.preventDefault();
        axios.post('main',{"name":this.state.name,"sign":this.state.team})
        .then((response)=>{
            console.log(response);
            this.setState({
                result2:response.data.result,
                message2:response.data.message,                
            });
			
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    send3=(event)=>{
     alert(this.token.value);
        event.preventDefault();
        axios.post('main',{"name":this.token.value,"sign":this.state.team})
        .then((response)=>{
            console.log(response);
            this.setState({
                result3:response.data.result,
                message3:response.data.message,                
            });
			
        })
        .catch((error)=>{
            console.log(error);
        });
    }

    state = {
      name: "React",
      team : "team",
      result1 : 0,
	  result2 : 0,
	  result3 : 0,
      message1 : "no message",
      message2 : "no message",
      message3 : "no message"
    };
    onChangeTeam1 =(event)=> {	 
      console.log(event.target.name); 
      this.setState({team:event.target.name});
      
  	}
    onChangeValue1 =(event)=> {	  
      this.setState({name:event.target.value});
      console.log(this.state.team);
  	} 
  	
  	onChangeTeam2 =(event)=> {	 
      console.log(event.target.name); 
      this.setState({team:event.target.name});
      
  	}
	onChangeValue2 =(event)=> {	  
      this.setState({name:event.target.value});
      console.log(this.state.team);
  	} 
  	
  	onChangeTeam3 =(event)=> {	 
      console.log(event.target.name); 
      this.setState({team:event.target.name});
      
  	}
  	
  	
	
  	
    render(){
        return(
	     <div>
            <div>
                <h2>EthereumNetwork 연결 해보세요</h2>   
                <button onClick={this.login} > 로그인 </button>  
                <button onClick={this.createUser} name="team1"> 회원가입 </button>         
            </div>
            <br/><br/>
			<div> 
				<a href="#team1"> 1조 </a> |
				<a href="#team2"> 2조 </a> |
				<a href="#team3"> 3조 </a> |
				<a href="#team4"> 4조 </a> |
				<a href="#team5"> 5조 </a> |
				<a href="#team6"> 6조 </a> |
			</div>
			<br/><br/>
			<div id="team1">
				<a name="team1"> 1조 [아마곗돈] </a>
				<button onClick={this.onChangeTeam1} name="team1"> 1조 서비스 받기 </button>
				<br/><br/>
    			<form onSubmit={this.send1}>				
					<div onChange={this.onChangeValue1}>
				        <input type="radio" value="노트북계" name="team1" /> 노트북계
				        <input type="radio" value="쌍수계" name="team1" /> 쌍수계
				        <input type="radio" value="여행계" name="team1" /> 여행계
				    </div>
				   <input type="submit" value="입금하기" />
			    </form>
                {this.state.message1} : 총 곗돈 : {this.state.result1}
			</div>
			<div id="team2">
				<a name="team2"> 2조 [키다리아저씨]</a>
				<button onClick={this.onChangeTeam2} name="team2"> 2조 서비스 받기 </button>
				<br/>
				<form onSubmit={this.send2}>	
				    <div onChange={this.onChangeValue2}>			   
				      <input type="radio" name="team2" value="밀알복지재단"/>밀알 복지 재단<br/>
				      <input type="radio" name="team2" value="삼육재단"/>삼육 재단<br/>
				      <input type="radio" name="team2" value="한국유기동물복지 협회"/>한국 유기동물 복지 협회<br/>
				    </div>
					<input type="submit" value="후원하기"/>
				 </form>
				{this.state.message2} : 총 후원금 : {this.state.result2}
			</div>
			<div id="team3">
				<a name="team3"> 3조 </a>
				<button onClick={this.onChangeTeam3} name="team3"> 3조 서비스 받기 </button>
				 <br/>
				   <b>장동건</b>님과의 거래진행중...
				   <br/>
				   <br/>
				   <img alt=""
				      src="https://funshop.akamaized.net/abroad/025/12727/nike-adapt-bb_0.jpg"
				      width="400" border="1"/>
				   <br/>
				   <br/> 상품명 : 나이키
				   <br/> 거래 가격 : 5,000원
				   <br/>
				   <br/>
				   <form onSubmit={this.send3}>
				       <b>토큰</b> 
				       <input ref={ref=>this.token=ref} /><input type="submit" value="토큰 보내기"/>
				   </form>
				   {this.state.message3} : 총 토큰: {this.state.result3}
			</div>
			<div>
				<a name="team4"> 4조 </a>
			</div>
			<div>
				<a name="team5"> 5조 </a>
			</div>
			<div>
				<a name="team6"> 6조 </a>
			</div>
		  </div>
        );
    }
}
class HyperledgerNetwork extends Component{  
 render(){
        return(
            <div>
                <h2>HyperledgerNetwork 연결 해보세요</h2>                
            </div>
        );
    }
}
    ReactDOM.render(
    (<Router>
        <Route path="/" component={Main} >   
            <Route path="bitcoin" component={BitcoinNetwork}/>
            <Route path="ethereum" component={EthereumNetwork} />
            <Route path="hyperledger" component={HyperledgerNetwork} />
            <IndexRoute component={Home} />
        </Route>
    </Router>)
    ,document.getElementById("root")
);
