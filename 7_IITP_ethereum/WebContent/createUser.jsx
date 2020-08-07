class CreateUser extends React.Component{
 
    send=()=>{
 		axios.post('main',{"sign":"createUser","id":this.id.value,"p_w":this.pw.value,"name":this.name.value})
        .then((response)=>{
            console.log(response);
            alert(response.data.message);
            window.opener = null;
			window.open('', '_self');
			window.close();
			
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    
 
    render(){     
 
        const main={
 
            width:'500px',
 
            height:'300px',
 
            margin:'30px auto'
 
            }
 
        const first={
 
            width:'400px',
 
            height:'400px',
 
            boxShadow:'0 0 0 1px rgba(14,41,57,0.12),0 2px 5px rgba(14,41,57,0.44),inset 0 -1px 2px rgba(14,41,57,0.15)',
 
            float:'left',
 
            padding:'10px 50px 0',
 
            background:'linear-gradient(#fff,#f2f6f9)'
 
            }
       
 
            const label={
 
            fontSize:'17px'            
 
            }
 
            const     input={
 
            width:'400px',
 
            padding:'5px',
 
            marginTop:'10px',
 
            marginBottom:'10px',
 
            borderRadius:'5px',
 
            border:'1px solid #cbcbcb',
 
            boxShadow:'inset 0 1px 2px rgba(0,0,0,0.18)',
 
            fontSize:'16px'
 
            }
 
            const select={
 
                padding:'13px'
 
            }

 
            const input_type_submit={
 
            background:'linear-gradient(to bottom,#22abe9 5%,#36caf0 100%)',
 
            boxShadow:'inset 0 1px 0 0 #7bdcf4',
 
            border:'1px solid #0F799E',
 
            color:'#fff',
 
            fontSize:'19px',
            
 			fontWeight:'700',
 
            cursor:'pointer',
 
            textShadow:'0 1px 0 #13506D'            
 
            }
 
            const center={
 
                align:'center'
 
            }
 
            
 
        return (          
 
            <div style={main}>
 
                <div style={first}>
 
                
                
	                <h1>회원 가입</h1>
	 
	                <h4>Please fill all entries.</h4><hr/>
	 
	                <label style={label}>ID :</label>
	 
	                <input style={input}  placeholder="아이디" ref={ref=>this.id=ref}/>
	 
	                <label style={label}>PW :</label>
	 
	                <input style={input}  placeholder="패스워드" ref={ref=>this.pw=ref}/>
	 
	                <label style={label}>NAME :</label>
	 
	                <input style={input}  placeholder="이름" ref={ref=>this.name=ref}/>
	                
	                <input style={input_type_submit} onClick={this.send} type="submit" value="Send"/> 
	                
	             
                
 
                </div>
 
            </div>           
 
        )
 
    }
 
}
 
 
ReactDOM.render( 
 
    <CreateUser / > ,
 
    document.getElementById('createUser')
 
);
