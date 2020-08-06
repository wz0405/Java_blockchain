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
class EthereumNetwork extends Component{     
   send=(event)=>{
        alert(this.state.name);
        event.preventDefault();
        axios.post('main',{"name":this.state.name,"sign":this.state.team})
        .then((response)=>{
            console.log(response);
            this.setState({
                result:response.data.result,
                message:response.data.message,
                //acc2_balance:response.data.acc2_balance
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
                //acc2_balance:response.data.acc2_balance
            });
         
        })
        .catch((error)=>{
            console.log(error);
        });
    }

    state = {
      name: "React",
      team : "...",
      result : 0,
     result2 : 0,
      message : "no message",
      message2 : "no message"
    };
    onChangeValue =(event)=> {     
      this.setState({name:event.target.value,team:event.target.name});
      console.log(this.state.team);
     } 
   onChangeValue2 =(event)=> {     
      this.setState({name:event.target.value,team:event.target.name});
      console.log(this.state.team,this.state.name);
     } 
    render(){
        return(
        <div>
            <div>
                <h2>EthereumNetwork 연결 해보세요</h2>                
            </div>
         <div> 
            <a href="#team1"> 1조 </a> |
            <a href="#team2"> 2조 </a> |
            <a href="#team3"> 3조 </a> |
            <a href="#team4"> 4조 </a> |
            <a href="#team5"> 5조 </a> |
            <a href="#team6"> 6조 </a> |
         </div>
         <div id="team1">
            <a name="team1"> 1조 [아마곗돈] </a>
            <br/><br/>
            <form onSubmit={this.send}>            
               <div onChange={this.onChangeValue}>
                    <input type="radio" value="노트북계" name="team1" /> 노트북계
                    <input type="radio" value="쌍수계" name="team1" /> 쌍수계
                    <input type="radio" value="여행계" name="team1" /> 여행계
                </div>
               <input type="submit" value="입금하기" />
             </form>
                {this.state.message} : 총 곗돈 : {this.state.result}
         </div>
         <div id="team2">
            <a name="team2"> 2조 [키다리아저씨]</a><br/>
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
         <div>
            <a name="team3"> 3조 </a>
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