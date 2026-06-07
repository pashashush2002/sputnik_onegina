import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js"; 
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

const BASE_URL = 'http://localhost:8080/api';

export let options = {
    stages: [
        { duration: '60s', target: 10 }, // 10 пользователей
        { duration: '60s', target: 100 }, // 100 пользователей
        { duration: '60s', target: 10 }, // Снижение нагрузки до 10 пользователей
    ],
};

export default function () {
    let random = Math.floor(Math.random() * 100000);
    let constellation_name = "testConstellation";
    let satellite_name = 'testImageSat' + random;
    
    // Создание группировки и спутников
    let postRes = http.post(`${BASE_URL}/add-satellites`, JSON.stringify({
  constellationName: `${constellation_name}`,
  satelliteParams: [
    {
      type: "IMAGE",
      name: `${satellite_name}`,
      batteryLevel: 0.1,
      resolution: 0.1
    }
  ]
    }), {
        headers: {
            'Content-Type': 'application/json',
        }
    });

    check(postRes, {
        'post creation successful': (r) => r.status === 201,
    });

    // Создание миссий
    let pos2Res = http.post(`${BASE_URL}/missions`, JSON.stringify({
  "constellationName": `${constellation_name}`,
  "satelliteName": null,
  "constellation": false
    }), {
        headers: {
            'Content-Type': 'application/json',
        }
    });

    check(postRes, {
        'mission creation successful': (r) => r.status === 201,
    });


    // Получение списка спутников
    let postsRes = http.get(`${BASE_URL}/overview`, {
        headers: {
        }
    });

    check(postsRes, {
        'posts fetched successfully': (r) => r.status === 200,
    });

    // Удаление спутника
    let deleteRes = http.del(`${BASE_URL}/constellations/${constellation_name}/satellites/${satellite_name}`, null, {
        headers: {
        }
    });

    check(deleteRes, {
        'post deletion successful': (r) => r.status === 204,
    });

    sleep(1); // Задержка перед следующим пользователем
}
export function handleSummary(data) {
  return {
    'result.html': htmlReport(data),
    stdout: textSummary(data, { indent: ' ', enableColors: true }),
  }
}