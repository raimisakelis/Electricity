
//show author data start
// (function ($) {
//
//     $(document).ready(function () {
//
//         $('input#author_id_submit').click(function (e) {
//             e.preventDefault();//this line to prevent reload
//             console.log('show author data inside');
//             var id = $('#author_id').val();
//             author(id);
//         });
//
//         function author(item_id) {
//             $.ajax({
//                 type: 'GET',
//                 url: '../electricity/api/author/' + item_id,
//                 contentType: 'application/json; charset=UTF-8',
//                 success: function (data) {
//                     showAuthor(data);
//                 },
//                 error: function (error) {
//                     console.log(error);
//                 }
//             });
//         }
//
//         function showAuthor(data) {
//             console.log('showAuthor vidus');
//             var html = '';
//             html +=
//                 '<table>' +
//                 '<tr>' +
//                     '<th>ID</th>' +
//                     '<th>Autoriaus vardas</th>' +
//                     '<th>Autoriaus pavardė</th>' +
//                 '</tr>'+
//                 '<tr>' +
//                      '<td>' + data.id + '</td>' +
//                     '<td>' + data.name + '</td>' +
//                     '<td>' + data.surname + '</td>' +
//                     '</tr>' +
//                 '</table>';
//
//             $('#author_table').html(html);
//         }
//     });
// })(jQuery);
//show author data end

//----------------------------------------------------------------------//

//show author list start
(function ($) {

    $(document).ready(function () {


        //automatiskai pasileidzia, kai uzsikrauna puslapis


        //console.log('show author list inside');

        var pageNo = 0;
        var pageSize = $('#page_size').val();
        console.log('puslapio dydis prieš duomenų traukimą' + pageSize);

        authorList(pageNo, pageSize);

        // $('button#list').click(function (e) {
        //     e.preventDefault();//this line to prevent reload
        //     console.log('show author list inside');
        //
        //     //var pageSize = 2;
        //     authorList(0, 3);
        //
        // });


        $('#page_size').change(function (e) {
            e.preventDefault();
            var newPageSize = $('#page_size').val();
            authorList(pageNo, newPageSize);
        });








        //get authors list data
        function authorList(pageNo, pageSize) {
           var skip = pageNo * pageSize;
            $.ajax({
                type: 'GET',
                url: '../electricity/api/author/list',
                data: {skip: skip, size: pageSize},
                success: function (data) {
                    console.log(data);
                    drawAuthorsTable(data, pageNo);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }


        //draw authors table
        function drawAuthorsTable(data, pageNo) {
            console.log('drawAuthorsTable inside');

            // var counter = 0;
            var html = '';
            html +=
                '<table>' +
                '<thead>' +
                '<tr>' +
                '<th>Autorius</th>' +
                '<th>Žymiausi darbai</th>' +
                '</tr>'+
                '</thead>' +
                '<tbody>';

            data.forEach(function (author) {
                //counter++;
                html +=
                    '<tr>' +
                    '<td>' + author.name +' '+ author.surname +'</td>' +
                    '<td>';

                author.works.forEach(function (work){
                    html +=
                        '<p>' + work.yearOfWork + '  ' + work.contentOfTheWork + '</p>';
                });

                html +=
                    '</td>' +
                    '</tr>';
            });

            html +=
                '</tbody>' +
                '<tfoot></tfoot>' +
                '</table>';




            var pageSize = $('#page_size').val();

            //console.log('skaitliukas skaiciuja, kiek atsinesa irasu' + counter);
            console.log('puslapio dydis lenteles viduje ' + pageSize);


            if (pageNo > 0) {
                html += '<button type="submit" id="bt_back">Back</button>';
            }

            html += '<button type="submit" id="bt_next">Next</button>';



            // if (counter < pageSize){
            //     $('button#bt_next').addClass('hidden');
            // }







            $('.table_wrapper').html(html);



            //go forward and backward
            $('#bt_next').click(function () {
                //e.preventDefault();//this line to prevent reload
                authorList(pageNo + 1, pageSize);
            });

            $('#bt_back').click(function () {

                console.log('back mygtuko vidus ');
                //e.preventDefault();//this line to prevent reload
                authorList(pageNo > 0 ? pageNo - 1 : 0, pageSize);
            });


        }


        // function options(){
        //
        //
        //     html +=  '<select id="page_size">'+
        //         '<option value="2">'+2+'</option>'+
        //         '<option value="2">'+3+'</option>'+
        //         '<option value="2">'+4+'</option>'+
        //         '</select>';
        // }





    });
})(jQuery);
//show author data end

//----------------------------------------------------------------------//

//save new author start
(function ($) {

    $(document).ready(function () {

        $('button#save').click(function (e) {
            e.preventDefault();//this line to prevent reload
            console.log('save author inside');

            var author = '{' +
                '"name":"' + 'Antanas' + '"' +
                ', "surname":"' + 'Fontanas' + '"}';

            $.ajax({
                type: 'POST',
                url: '../electricity/api/author/save',
                data: author,
                contentType: 'application/json; charset=UTF-8',
                success: function () {
                    console.log('success')
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
})(jQuery);
//save new author end

//----------------------------------------------------------------------//

//delete author start

(function ($) {

    $(document).ready(function () {
        $('button#delete').click(function (e) {
            console.log('delete author inside');
            e.preventDefault();

            var authorId = 15;

            $.ajax({
                method: 'DELETE',
                url: '../electricity/api/author/delete/' + authorId,
                contentType: 'application/json; charset=UTF-8',
                success: function () {
                    console.log('deleted');
                },

                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
})(jQuery);
//delete author end

//----------------------------------------------------------------------//























//show author list start
// (function ($) {
//
//     $(document).ready(function () {
//
//         $('input#authors_list_submit').click(function (e) {
//             e.preventDefault();//this line to prevent reload
//             console.log('praejo show author list pirmas');
//
//             //var pageSize = 2;
//             //authorList(0, 2);
//
//         });
//
//         function authorList(pageNo, pageSize) {
//
//             var skip = pageNo * pageSize;
//             $.ajax({
//                 type: 'GET',
//                 url: '../electricity/api/author/list',
//                 data: {skip: skip, size: pageSize},
//                 success: function (data) {
//                     authorsTable(data, pageNo, pageSize);
//                 },
//                 error: function (error) {
//                     console.log(error);
//                 }
//             });
//         }
//
//         function authorsTable(data, pageNo, pageSize) {
//             console.log('authorList vidus');
//             var html = '';
//             html +=
//                 '<table>' +
//                 '<tr>' +
//                 '<th>ID</th>' +
//                 '<th>Autoriaus vardas</th>' +
//                 '<th>Autoriaus pavardė</th>' +
//                 '</tr>';
//
//             data.forEach(function (item) {
//                 html +=
//                     '<tr>' +
//                     '<td>' + item.id + '</td>' +
//                     '<td>' + item.name + '</td>' +
//                     '<td>' + item.surname + '</td>' +
//                     '</tr>';
//             });
//
//             html += '</table>';
//
//
//
//
//             if (pageNo > 0) {
//                 html += '<button id="bt-back">Back</button>';
//             }
//             html += '<button id="bt-next">Next</button>';
//
//             $('#author_list_table').html(html);
//
//             //go forward
//             $('#bt-next').click(function () {
//                 authorList(pageNo + 1, pageSize);
//             });
//
//             //go backward
//             $('#bt-back').click(function () {
//                 authorList(pageNo > 0 ? pageNo - 1 : 0, pageSize);
//             });
//
//
//
//
//         }
//
//
//         //iki čia veikia
//
//
//
//         //pop-up form for test
//
//         // function getTestForm() {
//         //     console.log('vidus add test');
//         //     var html = '';
//         //     html +=
//         //         '<form method="post">' +
//         //         '<input name ="lector-id" placeholder="Lector id">' +
//         //         '<input name ="test-name" placeholder="Test name">' +
//         //         '<input name ="test-level" placeholder="Test level">' +
//         //         '<input name ="questions-order" placeholder="Questions order">' +
//         //         '<button type="submit" id="add-test-to-database">add</button>' +
//         //         '</form>';
//         //
//         //     $('#test-form').html(html);
//         //
//         //     $('#add-test-to-database').click(function (e) {
//         //         //e.preventDefault();
//         //         addToDatabase();
//         //
//         //     });
//         //
//         // }
//         //
//         // //add new test into database
//         // function addToDatabase() {
//         //     console.log('add to base vidus');
//         //
//         //     var test = '{' +
//         //         '"lectorId":' + $('input[name=lector-id]').val() +
//         //         ', "testName":"' + $('input[name=test-name]').val() + '"' +
//         //         ', "testLevel":' + $('input[name=test-level]').val() +
//         //         ', "questionsOrder":' + $('input[name=questions-order]').val() + '}';
//         //
//         //
//         //     $.ajax({
//         //         type: 'POST',
//         //         url: '../api/tests/save',
//         //         data: test,
//         //         contentType: 'application/json; charset=UTF-8',
//         //         success: function (newTest) {
//         //             console.log('success' + newTest);
//         //         },
//         //
//         //         error: function (error) {
//         //             console.log(error);
//         //         }
//         //     })
//         //
//         // }
//
//     });
// })(jQuery);











// console.log('drawTable vidus')
//     html +=
//         '<table>' +
//         '<tr>' +
//         '<th>ID</th>' +
//         '<th>Vardas</th>' +
//         '<th>Pavarde</th>' +
//         '<th>El. pastas</th>' +
//         '</tr>';
//
//     data.forEach(function (stud) {
//         html +=
//             '<tr>' +
//             // '<td><a href="#" onclick="employee(' + stud.empNo + ');return false;">' + stud.empNo + '</a></td>' +
//             '<td>' + /*stud.id*/ +'</td>' +
//             '<td>' + /*stud.name*/ +'</td>' +
//             '<td>' + stud.surname + '</td>' +
//             '<td>' + stud.email + '</td>' +
//             '</tr>';
//     });
//
//     html += '</table>';































//delete test from database
(function ($) {

    $(document).ready(function () {
        $('#test-delete').click(function (e) {
            console.log('vidus delete');
            e.preventDefault();

            var testId = 83;

            $.ajax({
                method: 'DELETE',
                url: '../api/tests/delete/' + testId,
                contentType: 'application/json; charset=UTF-8',
                success: function () {
                    console.log('deleted');
                },

                error: function (error) {
                    console.log(error);
                }
            });


        });

    });
})(jQuery);


(function ($) {

    $(document).ready(function () {


        $('#add-tests-to').click(function (e) {
            e.preventDefault();
            console.log('add to base vidus');
            // var test = {
            //     lectorId: $('[lector-id]').val(),
            //     testName: $('[test-name]').val(),
            //     testLevel: $('[test-level]').val(),
            //     questionsOrder: $('[questions-order]').val()
            // };


            var lectId = $('[lector-id]').val();
            var name = $('[test-name]').val();
            var level = $('[test-level]').val();
            var order = $('[questions-order]').val();


            // var lectId = 1;
            // var name = 'dusimtasis';
            // var level = 5;
            // var order = 2;


            var test = '{' +
                '"lectorId":' + lectId +
                ', "testName":"' + name + '"' +
                ', "testLevel":' + level +
                ', "questionsOrder":' + order + '}';


            $.ajax({
                type: 'POST',
                url: '../api/tests/save',
                data: test,
                contentType: 'application/json; charset=UTF-8',
                success: function (newTest) {
                    console.log('success' + newTest);
                },

                error: function (error) {
                    console.log(error);
                }
            })

        });


    });
})(jQuery);


// (function ($) {
//
//     $(document).ready(function () {
//
//
//
//         $('#add-test').click(function (e) {
//             console.log('vidus add test');
//
//             e.preventDefault();
//
//
//
//             var html = '';
//
//             html +=
//                 '<form action="" method="post">' +
//                 '<input name ="lector-id" placeholder="Lector id">' +
//                 '<input name ="test-name" placeholder="Test name">' +
//                 '<input name ="test-level" placeholder="Test level">' +
//                 '<input name ="questions-order" placeholder="Questions order">' +
//                 '<button id="add-test-to-database">add</button>' +
//                 '</form>';
//
//             $('#test-form').html(html);
//
//         });
//
//
//         $('#add-test-to-database').click(function (e) {
//             e.preventDefault();
//
//         });
//
//
//     });
// })(jQuery);


//kompaktiskesne forma lentels

// (function ($) {
//
//     $(document).ready(function () {
//
//         $('#all_test').click(function (e) {
//             e.preventDefault();//this line to prevent reload
//
//             tests(0, 2);
//
//         });
//
//         function tests(pageNo, pageSize) {
//             var skip = pageNo * pageSize;
//             var titles = ['ID', 'Lector ID', 'Test name', 'Test level', 'Questions order'];
//             var dataKeys = ['id', 'lectorId', 'testName', 'testLevel', 'questionsOrder'];
//             var containerName = 'alltable';
//             $.getJSON('../api/tests/list', {skip: skip, size: pageSize})
//                 .done(function (data) {
//                     console.log('praejo i vidu ');
//                     drawTable(titles, dataKeys, data, pageNo, pageSize, containerName);
//                 });
//         }
//
//
//         //function responsible for table
//         function drawTable(titles, dataKeys, data, pageNo, pageSize, containerName) {
//             console.log('drawTable vidus');
//
//             var html = '';
//             if (pageNo > 0) {
//                 html += '<button id="bt-back">Back</button>';
//
//             }
//             html += '<button id="bt-next">Next</button>';
//             html += '<table><tr>';
//
//             for (var i = 0; i < titles.length; i++) {
//                 html += '<th>' + titles[i] + '</th>';
//             }
//             html += '</tr>';
//
//             $(data).each(function (key, value) {
//                 html += '<tr>';
//                 for (var i = 0; i < dataKeys.length; i++) {
//                     html += '<td>' + eval('value.' + dataKeys[i]) + '</td>';
//                 }
//                 html += '</tr>';
//             });
//
//             html += '</table>';
//             $('#' + containerName).html(html);
//             $('#' + containerName + ' #bt-next').click(function () {
//                 tests(pageNo + 1, pageSize);
//             });
//             $('#' + containerName + ' #bt-back').click(function () {
//                 tests(pageNo > 0 ? pageNo - 1 : 0, pageSize);
//             });
//         }
//     });
// })(jQuery);










