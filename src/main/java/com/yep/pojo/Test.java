package main.java.com.yep.pojo;

import java.util.List;

public class Test {
    /**
     * total : 30
     * rows : [{"productid":"FI-SW-01","productname":"Koi","unitcost":10,"status":"P","listprice":36.5,"attr1":"Large","itemid":"EST-1"},{"productid":"K9-DL-01","productname":"Dalmation","unitcost":12,"status":"P","listprice":18.5,"attr1":"Spotted Adult Female","itemid":"EST-10"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":38.5,"attr1":"Venomless","itemid":"EST-11"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":26.5,"attr1":"Rattleless","itemid":"EST-12"},{"productid":"RP-LI-02","productname":"Iguana","unitcost":12,"status":"P","listprice":35.5,"attr1":"Green Adult","itemid":"EST-13"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":158.5,"attr1":"Tailless","itemid":"EST-14"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":83.5,"attr1":"With tail","itemid":"EST-15"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":23.5,"attr1":"Adult Female","itemid":"EST-16"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":89.5,"attr1":"Adult Male","itemid":"EST-17"},{"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92,"status":"P","listprice":63.5,"attr1":"Adult Male","itemid":"EST-18"},{"productid":"FI-SW-01","productname":"Koi","unitcost":10,"status":"P","listprice":36.5,"attr1":"Large","itemid":"EST-1"},{"productid":"K9-DL-01","productname":"Dalmation","unitcost":12,"status":"P","listprice":18.5,"attr1":"Spotted Adult Female","itemid":"EST-10"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":38.5,"attr1":"Venomless","itemid":"EST-11"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":26.5,"attr1":"Rattleless","itemid":"EST-12"},{"productid":"RP-LI-02","productname":"Iguana","unitcost":12,"status":"P","listprice":35.5,"attr1":"Green Adult","itemid":"EST-13"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":158.5,"attr1":"Tailless","itemid":"EST-14"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":83.5,"attr1":"With tail","itemid":"EST-15"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":23.5,"attr1":"Adult Female","itemid":"EST-16"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":89.5,"attr1":"Adult Male","itemid":"EST-17"},{"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92,"status":"P","listprice":63.5,"attr1":"Adult Male","itemid":"EST-18"},{"productid":"FI-SW-01","productname":"Koi","unitcost":10,"status":"P","listprice":36.5,"attr1":"Large","itemid":"EST-1"},{"productid":"K9-DL-01","productname":"Dalmation","unitcost":12,"status":"P","listprice":18.5,"attr1":"Spotted Adult Female","itemid":"EST-10"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":38.5,"attr1":"Venomless","itemid":"EST-11"},{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12,"status":"P","listprice":26.5,"attr1":"Rattleless","itemid":"EST-12"},{"productid":"RP-LI-02","productname":"Iguana","unitcost":12,"status":"P","listprice":35.5,"attr1":"Green Adult","itemid":"EST-13"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":158.5,"attr1":"Tailless","itemid":"EST-14"},{"productid":"FL-DSH-01","productname":"Manx","unitcost":12,"status":"P","listprice":83.5,"attr1":"With tail","itemid":"EST-15"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":23.5,"attr1":"Adult Female","itemid":"EST-16"},{"productid":"FL-DLH-02","productname":"Persian","unitcost":12,"status":"P","listprice":89.5,"attr1":"Adult Male","itemid":"EST-17"},{"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92,"status":"P","listprice":63.5,"attr1":"Adult Male","itemid":"EST-18"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * productid : FI-SW-01
         * productname : Koi
         * unitcost : 10.0
         * status : P
         * listprice : 36.5
         * attr1 : Large
         * itemid : EST-1
         */

        private String productid;
        private String productname;
        private double unitcost;
        private String status;
        private double listprice;
        private String attr1;
        private String itemid;

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public double getUnitcost() {
            return unitcost;
        }

        public void setUnitcost(double unitcost) {
            this.unitcost = unitcost;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getListprice() {
            return listprice;
        }

        public void setListprice(double listprice) {
            this.listprice = listprice;
        }

        public String getAttr1() {
            return attr1;
        }

        public void setAttr1(String attr1) {
            this.attr1 = attr1;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }
    }
}
