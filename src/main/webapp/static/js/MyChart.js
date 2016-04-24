$(window).load(
function() {
var ctx = document.getElementById('canvas').getContext('2d');
var data = {
    labels: ["12500", "2500", "625", "125", "25", "5", "0"],
    datasets: [
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            data: [0.3, 0.5 , 0.9, 1.1, 2,6,14]
        }
    ]
};
var myLineChart  = new Chart(ctx).Line(data, null);
     }
 );   