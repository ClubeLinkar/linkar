angular.module("linkar")
    .directive('uf', function() {
        return {
            replace: true,
            restrict: 'AE',
            scope: false,
            template: function (element, attrs) {
                return '<select>' +
                     '<option>AC</option>' +
                     '<option>AL</option>' +
                     '<option>AP</option>' +
                     '<option>AM</option>' +
                     '<option>BA</option>' +
                     '<option>CE</option>' +
                     '<option>DF</option>' +
                     '<option>ES</option>' +
                     '<option>GO</option>' +
                     '<option>MA</option>' +
                     '<option>MT</option>' +
                     '<option>MS</option>' +
                     '<option>MG</option>' +
                     '<option>PR</option>' +
                     '<option>PB</option>' +
                     '<option>PA</option>' +
                     '<option>PE</option>' +
                     '<option>PI</option>' +
                     '<option>RJ</option>' +
                     '<option>RN</option>' +
                     '<option>RS</option>' +
                     '<option>RO</option>' +
                     '<option>RR</option>' +
                     '<option>SC</option>' +
                     '<option>SE</option>' +
                     '<option>SP</option>' +
                     '<option>TO</option>' +
                     '</select>';
            }
        };
    });
