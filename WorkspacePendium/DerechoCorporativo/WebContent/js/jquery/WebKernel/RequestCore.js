$(document).ready(ready);
var RequestCore = null;

function ready() {
    RequestCore = new ClassRequestCore();
    //iLinkJsCore(''); 
}

function iLinkJsCore(data) {
    data = eval("(" + data + ")");
    var filters = data.Filters;
    var mth = data.Mth;
    RequestCore.core(filters, mth);

}

var ClassRequestCore = function () {
    this.Storage = new Array();
    this.handler = "iControls/iPrueba.ashx?action=Prueba";
    this.SilverControl = "SCApp";

    this.core = function (filters, mth) {
        var result = this.inList(filters + ",MTH=" + mth);
        if (result != null) {
            this.publish(result);
            return;
        }

        var rq = { "id": this.Storage.length, "filters": filters + ",MTH=" + mth, "result": null, "mth": mth };
        this.Storage.push(rq);
        rq.filters = filters;
        this.requestCalculus(rq);
    }


    this.inList = function (filters) {
        var result = null;
        for (var i in this.Storage)
            if (this.Storage[i].filters == filters) {
                result = this.Storage[i].result;
                break;
            }
        return result;
    }

    this.requestCalculus = function (rq) {
        var args = "Filters=" + rq.filters + "&RqId=" + rq.id + "&Mth=" + rq.mth;
        request(this.handler, args, function (data) {
            RequestCore.responseCalculus(data);
        });
    }

    this.responseCalculus = function (rp) {
        this.Storage[rp[6]].result = rp;
        this.publish(rp);
    }

    this.publish = function (data) {
        var control = document.getElementById(this.SilverControl);

        var bean = this.getIBean(data);

        control.Content.Costos.UpdateText(bean);
    }

    this.getIBean = function (data) {
        var bean = new Object();
        bean.MthHcBudget = data[0].BudgetFormat_SD;
        bean.MthHcActual = data[0].ActualFormat_SD;
        bean.MthHcVar = data[0].VarFormat_SD;
        bean.MthHcDif = data[0].DifFormat_SD;

        bean.YtdHcBudget = data[2].BudgetFormat_SD;
        bean.YtdHcActual = data[2].ActualFormat_SD;
        bean.YtdHcVar = data[2].VarFormat_SD;
        bean.YtdHcDif = data[2].DifFormat_SD;

        bean.TargetHcBudget = data[4].BudgetFormat_SD;
        bean.TargetHcActual = data[4].ActualFormat_SD;
        bean.TargetHcVar = data[4].VarFormat_SD;
        bean.TargetHcDif = data[4].DifFormat_SD;

        bean.MthCostoBudget = data[1].BudgetFormat_SDP;
        bean.MthCostoActual = data[1].ActualFormat_SDP;
        bean.MthCostoVar = data[1].VarFormat_SD;
        bean.MthCostoDif = data[1].DifFormat_SDP;

        bean.YtdCostoBudget = data[3].BudgetFormat_SDP;
        bean.YtdCostoActual = data[3].ActualFormat_SDP;
        bean.YtdCostoVar = data[3].VarFormat_SD;
        bean.YtdCostoDif = data[3].DifFormat_SDP;

        bean.TargetCostoBudget = data[5].BudgetFormat_SDP;
        bean.TargetCostoActual = data[5].ActualFormat_SDP;
        bean.TargetCostoVar = data[5].VarFormat_SD;
        bean.TargetCostoDif = data[5].DifFormat_SDP;

        return bean;
    }
}