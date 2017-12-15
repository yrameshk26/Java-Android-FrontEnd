var React = require('react');
var ReactDOM = require('react-dom');
require('./css/index.css');

//Module requires
var TodoItem = require('./todoitem');
var AddItem = require('./additem');

//Create component
var TodoComponent = React.createClass({
    getInitialState: function(){
            return{
                todos: ['step1', 'step2', 'step3', 'step4'],
                check: {name:'ramesh', company:'telus'}
            }
    },
    render: function(){
        var todos = this.state.todos;
        todos = todos.map(function(item,index){
            return(
                <TodoItem item={item} key={index} onDelete={this.onDelete}/>
            );
        }.bind(this));
        return(
            <div id="todo-list">
                <p><strong>Heading</strong></p>
                <ul>{todos}</ul>
                <AddItem onAdd={this.onAdd}/>
            </div>
        );
    },//render

    //Custom Functions
    onDelete:function(item){
        var updatedTodos = this.state.todos.filter(function(val,index){
            return item !== val;
        });
        this.setState({
            todos: updatedTodos
        });
    },

onAdd:function(item){
    var updatedTodos = this.state.todos;
    updatedTodos.push(item);
    this.setState({
        todos:updatedTodos
    })
}

});

//put component into html page
ReactDOM.render(<TodoComponent/>, document.getElementById('todo-wrapper'));
