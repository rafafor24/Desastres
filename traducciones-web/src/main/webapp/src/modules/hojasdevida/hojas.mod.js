(function (ng) {
    var mod = ng.module("hojaModule", ['ui.router']);
    mod.constant("hojasContext", "api/hojadevida");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hojasdevida/';
            var basePathIdioma = 'src/modules/idiomas/';
            $urlRouterProvider.otherwise("/hojasList");
            $stateProvider.state('hojas', {
                url: '/hojadevida',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hojas.html',
                        controller: 'hojaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hojasList', {
                url: '/list',
                parent: 'hojas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'hojas.list.html'
                    }
                }
            }).state('hojasDetail', {
                url: '/{hojaId:int}/detail',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'hojas.detail.html',
                        controller: 'hojaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hojaCreate', {
                url: '/create',
                parent: 'hojas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasNewCtrl'
                    }
                }
            }).state('hojaUpdate', {
                url: '/update/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/hojas.update.html',
                        controller: 'hojasUpdateCtrl'
                    }
                }
            }).state('hojaDelete', {
                url: '/delete/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/hojas.delete.html',
                        controller: 'hojasDeleteCtrl'
                    }
                }
            }).state('trayectoriaCreate', {
                url: '/create/empleado/{empleadoId:int}/hoja/{hojaId:int}/trayectorias',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/trayectoria.new.html',
                        controller: 'trayectoriasNewCtrl'
                    }
                }
            }).state('trayectoriaDelete', {
                url: '/delete/empleado/{empleadoId:int}/hoja/{hojaId:int}/trayecorias/{trayectoriaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    trayectoriaId:null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/trayectorias.delete.html',
                        controller: 'trayectoriasDeleteCtrl'
                    }
                }
            }).state('idiomaCreate', {
                url: '/create/empleado/{empleadoId:int}/hoja/{hojaId:int}/idiomas',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathIdioma + '/new/idiomas.new.html',
                        controller: 'idiomasNewCtrl'
                    }
                }
            }).state('idiomaDelete', {
                url: '/delete/empleado/{empleadoId:int}/hoja/{hojaId:int}/idiomas/{idiomaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    idiomaId:null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathIdioma + '/delete/idiomas.delete.html',
                        controller: 'idiomasDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
