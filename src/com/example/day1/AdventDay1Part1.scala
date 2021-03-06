package day1

object AdventDay1Part1 {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    var testSet = "1721\n979\n366\n299\n675\n1456";
    var finaltest = "1655\n1384\n1752\n1919\n1972\n1766\n1852\n1835\n1408\n1721\n1879\n1846\n1394\n1577\n1588\n1097\n1748\n1585\n765\n1375\n1806\n1785\n1824\n1847\n1037\n1531\n1989\n1570\n1625\n1600\n1832\n1927\n1691\n1593\n1936\n1812\n570\n1391\n1883\n1592\n1875\n1185\n1903\n855\n1331\n1742\n1884\n1356\n1944\n1994\n1556\n1271\n1572\n1661\n1914\n1905\n1581\n1634\n1252\n1657\n989\n1907\n1998\n1040\n1833\n1612\n1725\n1680\n1869\n1900\n1550\n1768\n1727\n1930\n1810\n1841\n734\n1779\n1774\n1825\n1446\n1259\n1552\n1310\n1885\n1689\n1929\n1959\n787\n1642\n1890\n1164\n1986\n1796\n1465\n1217\n1741\n1480\n1683\n1808\n1058\n1970\n1361\n2003\n1898\n1668\n1754\n1773\n1235\n1158\n1975\n1479\n1995\n1648\n1023\n883\n1553\n1658\n1794\n1747\n1978\n1268\n1966\n1192\n1886\n1471\n1548\n1819\n1551\n1958\n1732\n1676\n1745\n1501\n1858\n1652\n1596\n473\n1314\n1814\n1409\n1877\n1344\n1735\n1635\n1273\n871\n1643\n1599\n1565\n1695\n1803\n1764\n1778\n1823\n1831\n1701\n282\n1089\n1623\n1639\n1568\n1469\n1674\n1818\n1992\n1597\n1711\n1359\n1851\n1496\n1630\n1755\n1529\n1881\n1718\n1916\n1325\n1578\n1441\n1722\n1545\n1472\n1783\n1497\n1791\n1183\n1926\n1937\n1255\n1095\n1451\n1395\n1665\n1432\n1693\n1821\n1938\n1941\n2002"
    
    val list = finaltest.split("\n").map(s => s.toInt).toList
    val hashSet = list.toSet
    
    val valueOne = list.toStream
      .map(2020 - _)
      .filter(hashSet.contains(_))
      .headOption
      .getOrElse(-1)
    
    val valueTwo = 2020-valueOne;
    println(valueTwo)
    println(valueOne)
    println(valueOne*valueTwo)
  }

  def IterativeBinarySearch(arr: List[Int],
                            Element_to_Search: Int): Int = {
    var low = 0
    var high = arr.length - 1
    while (low <= high) {
      var middle = low + (high - low) / 2
      if (arr(middle) == Element_to_Search)
        return middle
      else if (arr(middle) > Element_to_Search)
        high = middle - 1
      else
        low = middle + 1
    }
    -1
  }
}
